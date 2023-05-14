package Controller.Actions;

import Controller.Controller;
import View.viewclasses.AboutView;

public class AboutAction implements Action {

  @Override
  public void execute(Controller controller) {
    AboutView aboutView = new AboutView(controller.getLanguage());
    aboutView.show();
  }
}
