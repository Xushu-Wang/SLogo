package View;

import Controller.ButtonObserver;
import Controller.Observer;
import Controller.TurtleObserver;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

@Deprecated
public abstract class LogoUI {

  private static final String DEFAULT_LANGUAGE = "English";
  private static final String DEFAULT_RESOURCE_PACKAGE = "UI/";
  private static final String DEFAULT_RESOURCE_FOLDER = "/UI/images/buttons/";
  private static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE + "css-files/" + "Default.css";
  private static final int DEFAULT_BUTTON_SIZE = 32;

  private ResourceBundle myResources;

  private ButtonObserver myButtonObserver;

  private Canvas myCanvas;

  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Constructor will just initialize the language properties files.
   *
   * @param language Language of the properties file you want to use.
   */
  public LogoUI(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Languages/." + language);
  }

  /**
   * Constructor will just initialize the language properties files. With no argument, by default
   * this is English
   */
  public LogoUI(){
    this(DEFAULT_LANGUAGE);
  }

  /**
   * Creates the main scene of the UI.
   *
   * @param primaryStage Stage obtained from the start applications "primaryStage".
   * @return scene with all elements contained.
   */
  public abstract Scene makeScene(Stage primaryStage);

  /**
   * Updates the language of the UI.
   */
  public abstract void updateLanguage(String language);

  /**
   * Retrieves user's text input from the view
   */
  public abstract String getCommandsText();

  /**
   *
   */
  public abstract void populateUserInput(String preset);

  /**
   * Loads the documentation in the view.
   * TODO: Decide on signature.
   */
  //public abstract void loadDocumentation();

  /**
   * Displays the history in the view.
   * TODO: Decide on signature.
   */
  //public abstract void displayHistory();

  /**
   * Displays saved variables in the view.
   * TODO: Decide on signature.
   */
  //public abstract void displaySavedVariables();

  /**
   * Displays saved methods in the view.
   * TODO: Decide on the signature.
   */
  //public abstract void displaySavedFunctions();


  /**
   * Sets the necessary observers to handle user input
   *
   * @param buttonObserver Observer that implements methods to handle different buttons being
   *                       clicked
   */
  public void setButtonObserver(ButtonObserver buttonObserver) {
    myButtonObserver = buttonObserver;
  }

  /**
   * Debug alert just to show that events are working.
   *
   * @param testString string to display.
   */
  public void displayDebug(String testString) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(testString);
    alert.setContentText("This button sure does work! " + testString);
    alert.showAndWait();
  }


  /**
   * Generates window to alert user that an error has occurred
   *
   * @param message Message to be displayed in error window
   */
  public void displayError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error occurred");
    alert.setContentText(message);
    alert.showAndWait();
  }

  public TurtleObserver getCanvas() {
    return myCanvas;
  }

  // Taken from lab browser
  // makes a button using either an image or a label
  protected Button makeButton (String property) {
    // represent all supported image suffixes
    final String IMAGE_FILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
    Button result = new Button();
    String label = myResources.getString(property);
    if (label.matches(IMAGE_FILE_SUFFIXES)) {
      ImageView buttonImage = new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label)));
      buttonImage.setFitHeight(DEFAULT_BUTTON_SIZE);
      buttonImage.setFitWidth(DEFAULT_BUTTON_SIZE);
      result.setGraphic(buttonImage);
    }
    else {
      result.setText(label);
    }
    result.setOnAction(e -> {
      Button clickedButton = (Button) e.getSource();
      myButtonObserver.onEvent(clickedButton.getId());
    });
    result.getStyleClass().add("button");
    return (Button) setID(property, result);
  }

  protected void setMyCanvas(Canvas canvas){
    myCanvas = canvas;
  }

  protected Canvas getMyCanvas(){
    return myCanvas;
  }

  protected Node setID (String id, Node node) {
    node.setId(id);
    return node;
  }

  protected ResourceBundle getMyResources(){
    return myResources;
  }

  protected String getDefaultStylesheet(){
    return DEFAULT_STYLESHEET;
  }

  public abstract Observer getSideView(String infoView, String history);

//  public abstract void updateHistory(List<String> history);

//  public abstract Observer getHistoryView();
}
