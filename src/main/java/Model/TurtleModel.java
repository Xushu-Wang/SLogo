package Model;


/**
 * @Purpose: TurtleModel interface that defines Turtle behavior to be implemented by both Single and Group turtles
 * @author Jay Yoon
 *
 */
public interface TurtleModel {

  void setLocation(double x, double y);
  void updateAngle(double newAngle);
  void goForward(double distance);
  void goBackward(double distance);
  double calcDistanceMoved(double x, double y);
  void turnLeft(double delta);
  void turnRight(double delta);
  double setTowards(double x, double y);
  double calcAngleTurned(double newAngle);
  TurtleSpace getSpace();
  double getXCoordinate();
  double getYCoordinate();
  double getDirection();
  boolean getStatus();
  void show();
  void hide();

}
