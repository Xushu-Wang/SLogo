package Controller.Actions;

import Controller.Controller;

public class ClearAction implements Action{

  public ClearAction(){
    // doesn't do anything.
  }


  @Override
  public void execute(Controller controller) {
    controller.clearCanvas();
  }
}
