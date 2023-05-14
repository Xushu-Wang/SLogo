package Controller;

public interface TurtleObserver {

  void updateOnPosition(int id, double x, double y);
  void updateOnDirection(int id, double angle);
  void updateOnVisibility(int id, boolean status);
}
