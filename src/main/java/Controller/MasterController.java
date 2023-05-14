package Controller;

import View.viewclasses.LanguageSelectionView;
import View.viewclasses.MainView;
import java.awt.Button;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;

public class MasterController implements ButtonObserver {
  private String myLanguage;
  private LanguageSelectionView myLanguageSelectionView;

  public MasterController() {
    // Does not do anything
  }

  public void promptForLanguage(Stage stage) {
    myLanguageSelectionView = new LanguageSelectionView(stage);
    myLanguageSelectionView.register(this);
    stage.setScene(myLanguageSelectionView.makeScene());
    stage.show();
  }

  public void makeNewView() {
    MainView userInterface = new MainView(myLanguage);
    Stage stage = new Stage();
    stage.setScene(userInterface.makeScene());
    Controller controller = new Controller(userInterface, this);
    controller.initializeObservers();
    stage.show();
  }

  @Override
  public void onEvent(String buttonId) {
    myLanguage = buttonId;
    makeNewView();
  }

  public String getLanguage() {
    return myLanguage;
  }
}
