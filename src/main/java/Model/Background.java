package Model;

import Controller.BackgroundObserver;
import Controller.Observable;
import java.util.ArrayList;
import java.util.List;

public class Background implements Observable<BackgroundObserver> {
  private List<BackgroundObserver> observerList;

  private String color;

  private boolean clear;

  public Background(){
    color = "000000";
    clear = false;
    observerList = new ArrayList<>();
  }

  public void setColor(String c){
    color = c;
    notifyObservers();
  }

  public void clearScreen(){
    clear = true;
    notifyObservers();
  }

  @Override
  public void register(BackgroundObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void remove(BackgroundObserver observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for(BackgroundObserver b: observerList){
      b.clear(clear);
      b.onColor(color);
    }
  }
}
