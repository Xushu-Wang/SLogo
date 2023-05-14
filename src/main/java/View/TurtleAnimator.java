package View;

import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * TurtleAnimator will handle all animation needs of the turtle.
 *
 * Note that objects of this class can save animations that it creates. Whenever an "animate"
 * method is done, it's result is saved to the ArrayList, and is only deleted when explicitly
 * desired by the user using the "clearSavedAnimations" method.
 *
 * @author tmh85
 */
public class TurtleAnimator {

  //private final TurtleUI myTurtle;
  private final ArrayList<Transition> myAnimationList;
  private double mySpeedFactor;
  private TrailMaker myTrailMaker;
  private static final double DEFAULT_SPEED = 1.0/5.0;
  private static final double MAX_SPEED = 10.0;

  public TurtleAnimator() {
    //myTurtle = turtleToAnimate;
    myAnimationList = new ArrayList<>();
    mySpeedFactor = DEFAULT_SPEED;
  }

  /**
   * With animate the movement of a turtle based on its current position and given ending points.
   *
   * @param endX   ending horizontal point
   * @param endY   ending vertical point
   * @param turtle
   * @return path the turtle goes on.
   */
  public Transition animateLineMovement(double endX, double endY, Optional<Node> trail,
      TurtleUI turtle) {
    SequentialTransition lineAnimate = new SequentialTransition();
    animateTurtleInLine(endX, endY, lineAnimate, turtle);
    animateTrailInLine(endX, endY, trail, lineAnimate);

    return lineAnimate;
  }

  /**
   * With animate the movement of a turtle based on its current position and given ending points.
   *
   * @param endX   ending horizontal point
   * @param endY   ending vertical point
   * @param turtle
   * @return path the turtle goes on.
   */
  public Transition animateLineMovement(double endX, double endY, TurtleUI turtle) {
    SequentialTransition lineAnimate = new SequentialTransition();
    animateTurtleInLine(endX, endY, lineAnimate, turtle);

    return lineAnimate;
  }

  private void animateTurtleInLine(double endX, double endY, SequentialTransition currAnimation,
      TurtleUI turtle){
    double turtleStartingX = turtle.currentPosition().getWidth();
    double turtleStartingY = turtle.currentPosition().getHeight();
    Path turtlePath = createPath(turtleStartingX, turtleStartingY, endX, endY);
    PathTransition pt = new PathTransition(Duration.seconds(mySpeedFactor), turtlePath);
    myAnimationList.add(pt);
    currAnimation.getChildren().add(pt);
  }

  private void animateTrailInLine(double endX, double endY, Optional<Node> trail, SequentialTransition currAnimation){
    if (trail.isEmpty()){
      return;
    }
    FadeTransition trailAnimation = new FadeTransition(Duration.seconds(mySpeedFactor));
    trailAnimation.setNode(trail.get());
    trailAnimation.setFromValue(0.0);
    trailAnimation.setToValue(1.0);
    currAnimation.getChildren().add(trailAnimation);
    myAnimationList.add(trailAnimation);

  }

  private Path createPath(double turtleStartingX, double turtleStartingY, double endX, double endY){
    Path path = new Path();
    path.getElements().addAll(
        new MoveTo(turtleStartingX, turtleStartingY),
        new LineTo(endX, endY)
    );
    return path;
  }

  /**
   * Animates the rotation of a turtle from its original angle to a given angle.
   *
   * @param rotateBy angle to rotate by
   * @param turtle
   * @return RotateTransition of the animation
   */
  public Transition animateRotation(double rotateBy, TurtleUI turtle) {
    RotateTransition rt = new RotateTransition(Duration.seconds(mySpeedFactor));
    rt.setFromAngle(turtle.currentRotation());
    rt.setToAngle(rotateBy);
    //rt.setByAngle(rotateBy);

    myAnimationList.add(rt);
    return rt;
  }

  //public Transition animateArcMovement{}
  //public Path drawLine{}
  //public Path changeLineColor{}

  /**
   * Given a transition element, make a SequentialTransition animation out of it.
   *
   * @param element some transition animation
   * @param turtle
   * @return SequentialTransition of the animation with the turtle added.
   */
  public SequentialTransition makeAnimation(Transition element, TurtleUI turtle) {
    SequentialTransition animation = new SequentialTransition();
    animation.getChildren().add(element);
    turtle.addToAnimation(animation);
    return animation;
  }

  /**
   * Plays all animations saved to the object.
   */
  public void playSavedAnimations(TurtleUI turtle) {
    Animation movement = this.makeAnimationsFromSaves(turtle);
    movement.play();
    this.clearSavedAnimations();
  }

  /**
   * Plays a given animation.
   * @param animation animation you wish to play
   */
  public void playAnimation(Animation animation) {
    animation.play();
  }

  /**
   * Creates a SequentialTransition based on the saved animation entries.
   * Note that animations will be played in the order of addition.
   * @return SequentialTransition of all the animations.
   */
  public Animation makeAnimationsFromSaves(TurtleUI turtle) {
    SequentialTransition animation = new SequentialTransition();
    animation.getChildren().addAll(myAnimationList);
    turtle.addToAnimation(animation);
    return animation;
  }

  /**
   * Clears all saved animations.
   */
  public void clearSavedAnimations() {
    myAnimationList.clear();
  }

  /**
   * Sets the speed of animations.
   * If speed given is higher than MAX_SPEED, the animation will just be skipped.
   * @param speed value from 0 to MAX_SPEED.
   */
  public void setSpeed(double speed) {
    mySpeedFactor = (speed >= MAX_SPEED ? 0 : 1 / speed);
  }

  /**
   * Gives you the current speed of animations.
   * @return speed of all animations
   */
  public double getSpeed() {
    return mySpeedFactor;
  }

}
