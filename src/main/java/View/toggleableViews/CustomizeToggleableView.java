package View.toggleableViews;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomizeToggleableView extends ToggleableView {

  public CustomizeToggleableView(int width, int height, String language) {
    super.setResources(language);
    super.setView(makeView());
    super.makeButton("CustomizeButton", getFromResources("Customize"));
    super.setDimensions(width, height);
  }

  @Override
  protected ScrollPane makeView() {
    VBox view = new VBox();
    view.setId("CustomizeView");
    view.getChildren().add(new Text("CUSTOMIZE!"));
    return new ScrollPane(view);
  }

  @Override
  protected void updateView(String context, String info) {
    // Does nothing (view not supposed to change)
  }
}
