package Controller;

import Controller.Actions.Action;
import Model.Interpreter;
import Model.Turtle;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TurtleSpace;
import View.viewclasses.MainView;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements ButtonObserver {

  private final MainView myMainView;
  private final Interpreter myParser;
  private final Turtle myModelTurtle;
  private final MasterController myMasterController;
  private ResourceBundle myButtonToActionResources;

  public Controller(MainView view, MasterController masterController) {
    TurtleSpace space = new TurtleSpace("English");
    myMasterController = masterController;
    myMainView = view;
    myParser = new Interpreter(space);
    myModelTurtle = new Turtle(space);
    myButtonToActionResources = ResourceBundle.getBundle("commands/ButtonToCommand");

  }

  public void initializeObservers() {
    myMainView.register(this);
    myModelTurtle.register(myMainView.getCanvas());
    myModelTurtle.addHistoryObserver(myMainView.getSideView("InfoPane", "History"));
  }

  @Override
  public void onEvent(String buttonId) {
    String className = myButtonToActionResources.getString(buttonId);
    try {
      Class<?> clazz = Class.forName(className);
      Action action = (Action) clazz.getDeclaredConstructor().newInstance();
      action.execute(this);
    } catch (ClassNotFoundException | InstantiationException | InvocationTargetException |
             IllegalAccessException | NoSuchMethodException e) {
      displayError("Illegal button name");
    }
  }

  public static File getUserInputFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File");
    fileChooser.setInitialDirectory(new File(
        System.getProperty("user.dir") + "/data/examples"));
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    return selectedFile;
  }

  public void runInputtedCommands() {
    String input = getCommandsFromView();
    try {
      myParser.processInput(input, myModelTurtle);
      myMainView.getSideView("CodePane", "Variables")
          .onUpdate("Variable", myModelTurtle.getSpace().variablesAsString());
      myMainView.getSideView("CodePane", "Functions")
          .onUpdate("Function", myModelTurtle.getSpace().functionsAsString());
    } catch (InvalidCommandException | MissingArgumentException | InvalidArgumentException | IllegalAccessException e) {
      myMainView.displayError(e.getMessage());
    }
  }

  public void clearCanvas(){
    myMainView.getCanvas().clear();
  }

  public String getCommandsFromView() {
    return myMainView.getCommandsText();
  }

  public void displayError(String message) {
    myMainView.displayError(message);
  }

  public void sendCommandsToView(String text) {
    myMainView.populateUserInput(text);
  }

  public void createNewView() {
    myMasterController.makeNewView();
  }

  public String getLanguage() {
    return myMasterController.getLanguage();
  }
}
