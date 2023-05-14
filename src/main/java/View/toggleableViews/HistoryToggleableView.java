package View.toggleableViews;

import Controller.Observer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryToggleableView extends ToggleableView {
  private VBox myView;
  private Deque<String> myCommandHistory;

  public HistoryToggleableView(int width, int height, String language) {
    super.setResources(language);
    super.setView(makeView());
    super.makeButton("HistoryButton", getFromResources("History"));
    super.setDimensions(width, height);
    myCommandHistory = new LinkedList<>();
  }

  @Override
  protected ScrollPane makeView() {
    myView = new VBox();
    myView.setId("HistoryView");
    myView.getChildren().add(new Text(super.getFromResources("Empty")));
    return new ScrollPane(myView);
  }

  @Override
  public void updateView(String context, String newCommand) {
    if (! context.equals("History")) {
      return;
    }
    if (myCommandHistory.size() == 0) {
      myView.getChildren().clear();
    }
    myCommandHistory.addFirst(newCommand);
    myView.getChildren().add(new Text(newCommand));
  }
}
