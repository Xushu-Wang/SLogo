package Model;

/**
 * @Purpose: TurtleSingle class that represents a single turtle
 * @author Jay Yoon
 *
 */
public class TurtleSingle implements TurtleModel {
  private int id;
  private double xPos;
  private double yPos;
  private double angle;
  private boolean displayStatus;
  private final TurtleSpace space;

  public TurtleSingle(int id, double x, double y, double direction, TurtleSpace space) {
    this.id = id;
    this.xPos = x;
    this.yPos = y;
    this.angle = direction;
    this.space = space;
  }

  @Override
  public void setLocation(double x, double y) {
    this.xPos = x;
    this.yPos = y;
  }

  @Override
  public void updateAngle(double newAngle) {
    this.angle = newAngle;
  }
  @Override
  public void goForward(double distance) {
    double updatedX = this.xPos + distance * Math.sin(Math.toRadians(this.angle));
    double updatedY = this.yPos - distance * Math.cos(Math.toRadians(this.angle));
    this.setLocation(updatedX, updatedY);
  }

  @Override
  public void goBackward(double distance) {
    double updatedX = this.xPos - distance * Math.sin(Math.toRadians(this.angle));
    double updatedY = this.yPos + distance * Math.cos(Math.toRadians(this.angle));
    this.setLocation(updatedX, updatedY);
  }

  @Override
  public double calcDistanceMoved(double x, double y) {
    double xDelta = Math.abs(x - this.xPos);
    double yDelta = Math.abs(y - this.yPos);
    return Math.hypot(xDelta, yDelta);
  }

  @Override
  public void turnLeft(double delta) {
    double newAngle = (this.angle - delta) % 360;
    this.updateAngle(newAngle);
  }

  @Override
  public void turnRight(double delta) {
    double newAngle = (this.angle + delta) % 360;

    this.updateAngle(newAngle);
  }

  @Override
  public double setTowards(double x, double y) {
    double xDelta = x - this.xPos;
    double yDelta = y - this.yPos;
    return Math.toDegrees(Math.atan2(xDelta, yDelta)) - this.angle;
  }

  @Override
  public double calcAngleTurned(double newAngle) {
    double change = 0;
    if (this.angle <= newAngle) {
      change = newAngle - this.angle;
    } else {
      change = (360 - this.angle) + newAngle;
    }
    return change;
  }

  @Override
  public TurtleSpace getSpace() {
    return this.space;
  }

  @Override
  public double getXCoordinate() {
    return this.xPos;
  }

  @Override
  public double getYCoordinate() {
    return this.yPos;
  }

  @Override
  public double getDirection() {
    return this.angle;
  }

  @Override
  public boolean getStatus() {
    return this.displayStatus;
  }

  @Override
  public void show() {
    this.displayStatus = true;
  }

  @Override
  public void hide() {
    this.displayStatus = false;
  }

}
