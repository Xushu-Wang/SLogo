package Model;

import Controller.Observable;
import Controller.PenObserver;
import java.util.ArrayList;
import java.util.List;

public class Pen implements Observable<PenObserver> {
  private List<PenObserver> observerList;
  private double width;
  private String color;
  private boolean activeStatus;
  public static final String DEFAULT = "000000";

  public Pen() {
    this.color = DEFAULT;
    this.activeStatus = true;
    observerList = new ArrayList<>();
  }

  public void activate() {
    this.activeStatus = true;
  }

  public void deactivate() {
    this.activeStatus = false;
  }

  public boolean getStatus() {
    return this.activeStatus;
  }

  public String getColor(){
    return color;
  }

  public void setColor(String c){
    color = c;
    notifyObservers();
  }

  public void setWidth(double w){
    width = w;
    notifyObservers();
  }

  @Override
  public void register(PenObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void remove(PenObserver observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for(PenObserver e: observerList){
      e.onColor(this.color);
      e.onSize(this.width);
      e.onStatus(this.activeStatus);
    }
  }
}
