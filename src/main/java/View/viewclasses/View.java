package View.viewclasses;

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

public abstract class View {
  private static final String DEFAULT_LANGUAGE = "English";
  private static final String DEFAULT_RESOURCE_PACKAGE = "UI/";
  private static final String DEFAULT_RESOURCE_FOLDER = "/UI/images/buttons/";
  private static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE + "css-files/" + "Default.css";

  private ResourceBundle myResources;
  private Stage myStage;

  public View() {
    this(DEFAULT_LANGUAGE);
  }

  public View(String language) {
    this(language, new Stage());
  }
  public View (Stage stage) {
    this(DEFAULT_LANGUAGE, stage);
  }

  public View(String language, Stage stage) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Languages/." + language);
    myStage = stage;
  }

  /**
   * Get value in already instantiated resource bundle through key
   * @param key value used to look up entry in properties file
   * @return String corresponding to inputted key in properties file
   */
  public String getFromResources(String key) {
    return myResources.getString(key);
  }

  /**
   * Display scene to user
   */
  public void show() {
    myStage.show();
  }

  /**
   * Hide scene from user
   */
  public void hide() {
    myStage.hide();
  }

  /**
   * Set custom title to be displayed on window
   * @param title title displayed on stage
   */
  public void setStageTitle(String title) {
    myStage.setTitle(title);
  }

  public void displayError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error occurred");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Set the specified ID to the node passed in as input
   * @param id ID that node will receive
   * @param node node that will get ID
   * @return node with same properties as original, but with updated ID
   */
  protected Node setID (String id, Node node) {
    node.setId(id);
    return node;
  }

  /**
   * Each subclass should implement how to generate its specific scene
   * @return Scene instance that will be added to stage and displayed
   */
  public abstract Scene makeScene();

  protected void addSceneToStage(Scene scene) {
    myStage.setScene(scene);
  }

  protected String getDefaultStylesheet(){
    return DEFAULT_STYLESHEET;
  }

  protected Button makeButton (String property, int width, int height) {
    // represent all supported image suffixes
    final String IMAGE_FILE_SUFFIXES = String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
    Button result = new Button();
    String label = myResources.getString(property);
    if (label.matches(IMAGE_FILE_SUFFIXES)) {
      ImageView buttonImage = new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label)));
      buttonImage.setFitWidth(width);
      buttonImage.setFitHeight(height);
      result.setGraphic(buttonImage);
    }
    else {
      result.setText(label);
    }
//    result.setOnAction(e -> {
//      Button clickedButton = (Button) e.getSource();
//      myButtonObserver.onEvent(clickedButton.getId());
//    });

    result.getStyleClass().add("button");
    return (Button) setID(property, result);
  }
}
