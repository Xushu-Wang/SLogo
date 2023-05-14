package View.debugclasses;

import Model.Turtle;
import View.TurtleUI;
import View.canvasclasses.DefaultCanvas;

public class DefaultCanvasTest extends DefaultCanvas {
  public DefaultCanvasTest(){
    super();
  }

  public DefaultCanvasTest(double windowWidth, double windowHeight){
    super(windowWidth, windowHeight);
  }

  @Override
  protected TurtleUI createTurtle(){
    ViewTurtleTest newTurtle = new ViewTurtleTest();
    return newTurtle;
  }

  public TurtleUI getTurtle(){
    return super.getMyTurtle();
  }
}
