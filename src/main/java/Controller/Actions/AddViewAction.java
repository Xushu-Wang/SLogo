package Controller.Actions;

import Controller.Controller;

public class AddViewAction implements Action {
  public AddViewAction() {
    // Does not do anything
  }


  @Override
  public void execute(Controller controller) {
    controller.createNewView();
  }
}
