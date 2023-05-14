package View.viewturtles;

import View.TurtleUI;
import View.viewclasses.MainView;
import java.awt.Dimension;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.CacheHint;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * Default implementation of the TurtleUI interface.
 *
 * @author tmh85
 * @author rb419
 */
public class ViewTurtle implements TurtleUI {

  public static final String RESOURCE_PATH = "/UI/images/turtles/";
  public static final String BOUNCER_IMAGE =
      RESOURCE_PATH + "DefaultTurtle.png";
  private static final Dimension DEFAULT_SIZE = new Dimension(50, 50);
  private int myID;
  private ImageView myTurtle;
  private Dimension myLocation;
  private Dimension myTurtleSize;
  private double myAngle;
  private boolean myDrawActive;

  public ViewTurtle() {
    initializeTurtle(0);
  }

  public ViewTurtle(int ID){
    initializeTurtle(ID);
  }

  private void initializeTurtle(int ID){
    myTurtle = new ImageView(new Image(MainView.class.getResourceAsStream(BOUNCER_IMAGE)));
    myTurtleSize = DEFAULT_SIZE;
    myTurtle.setFitWidth(myTurtleSize.getWidth());
    myTurtle.setFitHeight(myTurtleSize.getHeight());
    myLocation = new Dimension(0, 0);
    myAngle = 0.0;
    this.setPenDown();
    this.setTurtleID(ID);
    //setPosition(DEFAULT_LOCATION.width, DEFAULT_LOCATION.height);
  }

  @Override
  public void addToPane(Pane myPane) {
    myPane.getChildren().addAll(myTurtle);
  }

  @Override
  public void setPosition(double x, double y) {
    myTurtle.setX(x);
    myTurtle.setY(y);
    myLocation.setSize(x, y);
  }

  @Override
  public Dimension currentPosition() {
    return myLocation;
  }

  @Override
  public double currentRotation() {
    return myAngle;
  }

  @Override
  public void setRotation(double degrees) {
    myAngle = degrees;
    myTurtle.setRotate(degrees);
  }

  @Override
  public void addToAnimation(SequentialTransition theTransition) {
    theTransition.setNode(myTurtle);
  }

  @Override
  public void addToAnimation(ParallelTransition theAnimation) {
    theAnimation.setNode(myTurtle);
  }

  @Override
  public Dimension getSize() {
    return myTurtleSize;
  }

  @Override
  public void setSize(double x, double y) {
    myTurtleSize.setSize(x, y);
  }

  @Override
  public void changeImage(Image picture) {
    myTurtle.setImage(picture);
  }

  @Override
  public Image getImage() {
    return myTurtle.getImage();
  }

  @Override
  public void setColor(Paint color) {
    ColorInput colorEffect = new ColorInput(
        0,
        0,
        myTurtle.getImage().getWidth(),
        myTurtle.getImage().getHeight(),
        color
    );

    myTurtle.setEffect(colorEffect);
    // stack overflow recommended the below lines for speed.
    myTurtle.setCache(true);
    myTurtle.setCacheHint(CacheHint.SPEED);
  }

  @Override
  public Paint getCurrentColor() {
    //todo: check for errors
    return ((ColorInput) myTurtle.getEffect()).getPaint();
  }

  @Override
  public void setOpacity(double opacity) {
    myTurtle.setOpacity(opacity);
  }

  @Override
  public double getOpacity() {
    return myTurtle.getOpacity();
  }

  @Override
  public void setPenDown() {
    myDrawActive = true;
  }

  @Override
  public void pickPenUp() {
    myDrawActive = false;
  }

  public boolean isPenActive(){
    return myDrawActive;
  }

  /**
   * @see #setTurtleID
   * TODO: Return errors if less than 0
   */
  @Override
  public void setTurtleID(int ID) {
    myID = ID;
  }

  @Override
  public int getTurtleID() {
    return 0;
  }

}
