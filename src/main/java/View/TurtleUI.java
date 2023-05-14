package View;

import java.awt.Dimension;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * Internal API for the View turtle.
 *
 * @author tmh85
 */
public interface TurtleUI {

  /**
   * Adds the turtle to a given pane.
   *
   * @param myPane the pane we wish to add the turtle to.
   */
  void addToPane(Pane myPane);

  /**
   * Sets the position of the turtle with a given x and y.
   *
   * @param x x-position of the turtle.
   * @param y y-position of the turtle.
   */
  void setPosition(double x, double y);

  /**
   * Returns the current position of the turtle. Note that this will be coordinates assuming that
   * (0,0) is center.
   *
   * @return Dimension object containing x and y coordinates.
   */
  Dimension currentPosition();

  //public void changeForm();

  /**
   * Returns the current rotation of the turtle. This will be rotation assuming that 0 radians is
   * the turtle facing straight up. (so pi/2 should be facing left).
   *
   * @return angle of turtle in radians
   */
  double currentRotation();

  /**
   * Sets the current rotation of the turtle in radians.
   *
   * @param radians
   */
  void setRotation(double radians);

  /**
   * Add the turtle to a given SequentialTransition
   * @param theAnimation given SequentialTransition
   */
  void addToAnimation(SequentialTransition theAnimation);

  /**
   * Add the turtle to a given ParallelTransition
   * @param theAnimation given ParallelTransition
   */
  void addToAnimation(ParallelTransition theAnimation);

  /**
   * Returns the size of the turtle.
   * @return dimension object that contains the width and height of the turtle.
   */
  Dimension getSize();

  /**
   * Set the size of the turtle.
   * @param width desired width of the turtle
   * @param height
   */
  void setSize(double width, double height);

  /**
   * Change the image of the turtle.
   * @param picture image you want the turtle to be.
   */
  void changeImage(Image picture);

  /**
   * Return the current image of the turtle.
   * @return image of the turtle
   */
  Image getImage();

  /**
   * Change the color of the turtle.
   * @param color color you wish the turtle to be.
   */
  void setColor(Paint color);

  /**
   * Get the current color of the turtle.
   * @return the current color of the turtle.
   */
  Paint getCurrentColor();

  /**
   * Set the opacity of the turtle, from 0 to 1.
   * @param opacity value from 0 to 1.
   */
  void setOpacity(double opacity);

  /**
   * Gives you the current opacity of the turtle.
   * 1 is completely opaque, 0 is translucent.
   * @return opacity
   */
  double getOpacity();

  /**
   * Sets the pen down, allowing the turtle to draw a trail.
   */
  void setPenDown();

  /**
   * Picks the pen up, disabling the turtle from drawing trails.
   */
  void pickPenUp();

  /**
   * Tells you whether trails should be drawn or not.
   * @return true if active, false if not
   */
  boolean isPenActive();

  /**
   * Set the ID of the given turtle.
   * @param ID Positive integer greater than or equal to zero.
   */
  void setTurtleID(int ID);

  /**
   * Returns the ID of the given turtle
   * @return an integer greater than or equal to zero.
   */
  int getTurtleID();

}
