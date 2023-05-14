package Controller;

public interface PenObserver {
  void onSize(double width);
  void onColor(String color);
  void onStatus(boolean status);
}
