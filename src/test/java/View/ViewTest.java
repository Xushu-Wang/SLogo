package View;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

import Controller.Controller;
import Controller.MasterController;
import View.debugclasses.MainViewTest;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.base.NodeMatchers;
import util.DukeApplicationTest;

public class ViewTest extends DukeApplicationTest {

  private MainViewTest myView;
  private Controller myController;
  private MasterController myMasterController;
  private TextInputControl myTextArea;
  private Button myRunButton;
  private final static String HOME = "home";

  /**
   * Runs before each method to set the application up in a "fresh stage." Heavily inspired by Prof.
   * Duvall's code in example_testing
   *
   * @param stage
   */
  @Override
  public void start(Stage stage) {
    myMasterController = new MasterController();
    myView = new MainViewTest("English");
    stage.setScene(myView.makeScene());

    myController = new Controller(myView, myMasterController);
    myController.initializeObservers();

    stage.show();

    // Reused components
    myTextArea = lookup("#Input").query();
    myTextArea.clear();
    myRunButton = lookup("#RunButton").query();
  }

  /**
   * Tests forward turtle action with a regular value, cancelling that regular value with a negative
   * forward, and then decimally movement. Also checks if initial placement is zero.
   */
  @Test
  void testForwardTurtleAction() {
    String forwardZeroString = "fd 0";
    String forwardFiftyString = "fd 50";
    String forwardNegativeFiftyString = "fd -50";
    String forwardDecimallyString = "fd 78.45";
    // GIVEN, app first starts up
    // WHEN, text is typed and action is activated with ENTER key
    runInputPrompt(HOME);

    runInputPrompt(forwardZeroString);
    assertEquals(379.0, myView.getTurtle().currentPosition().getWidth());

    runInputPrompt(HOME);

    runInputPrompt(forwardFiftyString);
//    assertEquals(550.0, myView.getTurtle().currentPosition().getWidth());

    runInputPrompt(HOME);

    runInputPrompt(forwardNegativeFiftyString);
    assertEquals(450, myView.getTurtle().currentPosition().getWidth());

    runInputPrompt(HOME);
    // todo: verify what we wish to do with decimally values.
    // todo: Currently, this ceilings.
    runInputPrompt(forwardDecimallyString);
    assertEquals(579.0, myView.getTurtle().currentPosition().getWidth());
  }

  /**
   * Tests the rotation of the turtle with a regular left rotation, a right rotation to cancel out
   * the left rotation, and then a regular right rotation. Also checks if rotation is initially
   * zero.
   */
  @Test
  void testRotateTurtleAction() {
    String leftRotateString = "lt 50";
    String rightRotateString = "rt 50";
    // GIVEN, app first starts up
    // WHEN, text is typed and action is activated with ENTER key

    // Check if default rotation is zero.
    runInputPrompt("");
    assertEquals(0.0, myView.getTurtle().currentRotation());

    // Check if rotating left displays correctly.
    runInputPrompt(leftRotateString);
    assertEquals(50.0, myView.getTurtle().currentRotation());

    // Check if rotating left and then rotating right the same amount cancels out.
    runInputPrompt(rightRotateString);
    assertEquals(0, myView.getTurtle().currentRotation());

    // Check if rotating right displays correctly.
    runInputPrompt(rightRotateString);
    assertEquals(310, myView.getTurtle().currentRotation());
  }

  @Test
  void testInvalidCommandException() {
    String invalid = "invalid";
    runInputPrompt(invalid);
    verifyThat("OK", NodeMatchers.isVisible());
  }

  @Test
  void testMissingArgumentException() {
    String missing = "forward";
    runInputPrompt(missing);
    verifyThat("OK", NodeMatchers.isVisible());
  }

  @Test
  void testTurtleVisibility(){
    String makeTurtleInvisible = "hideturtle";
    String makeTurtleVisible = "showturtle";
    runInputPrompt(HOME);

    runInputPrompt(makeTurtleInvisible);
    assertEquals(0.0, myView.getTurtle().getOpacity());

    runInputPrompt(makeTurtleVisible);
    assertEquals(1.0, myView.getTurtle().getOpacity());
  }

  /**
   * Runs a requested prompt by entering it in the textarea and clicking the run button
   *
   * @param requestedPrompt
   */
  private void runInputPrompt(String requestedPrompt) {
    writeInputTo(myTextArea, requestedPrompt + "\n");
    clickOn(myRunButton);
  }

  //private void stepInputPrompt(String requestedPrompt){}

}
