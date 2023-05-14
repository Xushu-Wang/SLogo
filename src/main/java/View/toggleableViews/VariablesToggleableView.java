package View.toggleableViews;

import java.awt.Dimension;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariablesToggleableView extends ToggleableView {
  private VBox myView;

  public VariablesToggleableView(int width, int height, String language) {
    super.setResources(language);
    super.setView(makeView());
    super.makeButton("VariablesButton", getFromResources("Variables"));
    super.setDimensions(width, height);
  }

  @Override
  protected ScrollPane makeView() {
    myView = new VBox();
    myView.setId("Variables");
    myView.getChildren().add(new Text(getFromResources("Empty")));
    return new ScrollPane(myView);
  }

  @Override
  public void updateView(String context, String info) {
    if (! context.equals("Variable") || info.equals("")) {
      return;
    }
    myView.getChildren().clear();
    for (String variable : info.split("\\n")) {
      myView.getChildren().add(new Text(variable));
    }
  }
}
