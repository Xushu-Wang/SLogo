package Controller.Actions;

import Controller.Controller;

public class RunAction implements Action {
  public RunAction() {
    // Does not do anything
  }

  @Override
  public void execute(Controller controller) {
    controller.runInputtedCommands();
  }
}
