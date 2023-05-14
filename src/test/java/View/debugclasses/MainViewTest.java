package View.debugclasses;

import View.Canvas;
import View.TurtleUI;
import View.viewclasses.MainView;

public class MainViewTest extends MainView {

  public MainViewTest(String language) {
    super(language);
  }

  @Override
  protected Canvas createCanvas(double windowWidth, double windowHeight) {
    Canvas newCanvas = new DefaultCanvasTest(windowWidth, windowHeight);
    return newCanvas;
  }

  public TurtleUI getTurtle(){
    return super.getCanvas().getMyTurtle();
  }
}
