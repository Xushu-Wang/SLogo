package Model;

import Controller.Observable;
import Controller.ShapeObserver;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Observable<ShapeObserver> {

  private List<ShapeObserver> observerList;
  private double shape;

  public Shape(){
    shape = 0.0;
    observerList = new ArrayList<>();
  }


  @Override
  public void register(ShapeObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void remove(ShapeObserver observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for(ShapeObserver s: observerList){
      s.onShape(this.shape);
    }
  }
}
