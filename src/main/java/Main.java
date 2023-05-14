import Controller.Controller;
import Controller.MasterController;
import View.LogoUI;
import View.viewclasses.MainView;
import View.viewclasses.LanguageSelectionView;
import javafx.application.Application;
import javafx.stage.Stage;
import Controller.ButtonObserver;

/**
 * Only purpose is to start JavaFX.
 */
public class Main extends Application {

  /**
   * Start the program, give complete control to JavaFX.
   * <p>
   * Default version of main() is actually included within JavaFX, so this is not technically
   * necessary!
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    MasterController controller = new MasterController();
    controller.promptForLanguage(primaryStage);
  }
}
