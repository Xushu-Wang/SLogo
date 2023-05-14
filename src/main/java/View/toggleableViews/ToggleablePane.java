package View.toggleableViews;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class to represent pane to the side of main screen, with buttons to toggle different views
 * Each instance represents a pane, and keeps a list of all possible views (of type ToggleableView)
 */
public class ToggleablePane {
  private final String BUTTON_ID = "ButtonBar";

  private VBox myLayout;
  private List<ToggleableView> myViews;
  private ToggleableView myCurrentView;

  /**
   * Default constructor (initializes instance variables to default values)
   */
  public ToggleablePane() {
    myViews = new ArrayList<>();
    myLayout = new VBox();
    myLayout.getStyleClass().add("window");
    HBox buttonBar = new HBox();
    buttonBar.setId(BUTTON_ID);
    myLayout.getChildren().add(buttonBar);
  }

  /**
   * Add button to toggle view in the pane
   * @param view view to be added to the pane
   */
  public void addView(ToggleableView view) {
    if (myCurrentView == null) {
      myCurrentView = view;
    }
    myViews.add(view);
    view.getButton().setOnAction((e) -> {
      switchToView(view);
    });
    updateButtonBar();
    switchToView(view);
  }


  /**
   * Remove button to toggle view in the pane
   * @param view view to be removed from pane
   */
  public void removeView(ToggleableView view) {
    myViews.remove(view);
    updateButtonBar();
  }

  /**
   * Hide current view and display new one based on toggled button
   * @param newView new view to be displayed on button click
   */
  public void switchToView(ToggleableView newView) {
    myLayout.getChildren().remove(myCurrentView.getBox());
    for (ToggleableView view : myViews) {
      view.hideView();
    }
    myLayout.getChildren().add(newView.getBox());
    newView.showView();
    myCurrentView = newView;
  }

  private void updateButtonBar(){
    HBox topBar = new HBox();
    for (ToggleableView view : myViews){
      topBar.getChildren().add(view.getButton());
    }
    topBar.getStyleClass().add("sub-window");

    HBox outdatedButtons = (HBox) myLayout.lookup("#" + BUTTON_ID);
    myLayout.getChildren().add(myLayout.getChildren().indexOf(outdatedButtons), topBar);
  }

  public VBox getPane() {
    return myLayout;
  }

  public void setId(String id) {
    myLayout.setId(id);
  }

//  public void updateHistory(List<String> history) {
//    for (ToggleableView view : myViews) {
//      System.out.println("almost there!");
//      if (view instanceof HistoryToggleableView) {
//        view.update(history);
//      }
//    }
//  }

  public ToggleableView getView(String name) {
    Class<?> viewClass;
    try {
      viewClass = Class.forName("View.toggleableViews."  + name + "ToggleableView");
    } catch (ClassNotFoundException e) {
      return null;
    }
    for (ToggleableView view : myViews) {
      if (viewClass.isInstance(view)) {
        return view;
      }
    }
    return null;
  }
}
