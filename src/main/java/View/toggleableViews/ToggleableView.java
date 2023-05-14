package View.toggleableViews;

import Controller.Observer;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Class to represent the combo of a view in a ToggleablePane, and the button that shows that view
 */
public abstract class ToggleableView implements Observer {
  protected final int MAX_PANE_WIDTH = 50;
  private final String RESOURCES_FOLDER = "UI.Languages.";

  private Button myButton;
  private ScrollPane myView;
  private ResourceBundle myResources;

  /**
   * General constructor for abstract class (initialize instance variables)
   */
  public ToggleableView() {
    // Does not do anything
  }

  public void setResources(String language) {
    myResources = ResourceBundle.getBundle(RESOURCES_FOLDER + language);
  }

  protected String getFromResources(String key) {
    return myResources.getString(key);
  }

  /**
   * Make view for information that should be displayed when the corresponding button is toggled
   * @return VBox containing appropriately layed out information
   */
  protected abstract ScrollPane makeView();

  /**
   * Make button with no content associated with it
   * @return button with no predefined text / funcionality
   */
  protected Button makeBlankButton() {
    return new Button();
  }

  /**
   * Make button with text content that allows for view to be toggled
   */
  protected void makeButton(String id, String text) {
    myButton = new Button(text);
    myButton.setId(id);
  }


  /**
   * Make button with image that allows for view to be toggled
   * @return button to toggle view
   */
  protected void makeButton(String id, ImageView image) {
    myButton = new Button();
    myButton.setGraphic(image);
    myButton.setId(id);
  }

  /**
   *
   */
  protected abstract void updateView(String context, String info);

  @Override
  public void onUpdate(String context, String info) {
    this.updateView(context, info);
  }

  protected void hideView() {
    myView.setVisible(false);
    myButton.setDisable(false);
  }

  protected void showView() {
    myView.setVisible(true);
    myButton.setDisable(true);
  }

  public Button getButton() {
    return myButton;
  }

  protected void setView(ScrollPane view) {
    myView = view;
  }

  public Node getBox() {
    return myView;
  }

  public void setDimensions(double width, double height) {
    myView.setPrefSize(width, height);
    myView.setFitToWidth(true);
    myView.setFitToHeight(true);
    myView.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    myView.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    myView.setSnapToPixel(false);
    myView.setDisable(false);
    myView.setPannable(true);
    myView.setMouseTransparent(false);
    myView.setFocusTraversable(true);
  }
}
