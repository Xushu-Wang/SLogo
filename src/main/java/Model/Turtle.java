package Model;

import Controller.Observer;
import Controller.Observable;
import Controller.PenObserver;
import Controller.ShapeObserver;
import Controller.TurtleObserver;
import Model.TokenType.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Purpose: Turtle class that represents group of multiple turtles that follow Turtle behavior
 * @author Jay Yoon, Andy Wang, Rodrigo Guerreiro
 *
 */
public class Turtle implements TurtleModel, Observable<TurtleObserver> {
  private final Map<Integer, TurtleSingle> turtles;
  private final Map<Integer, Boolean> turtleStatus;
  private final List<TurtleObserver> observers;
  private final History commandHistory;
  private Shape shape;
  private Pen pen;
  private final TurtleSpace space;

  public Turtle(TurtleSpace space) {
    this.turtles = new HashMap<>();
    this.turtleStatus = new HashMap<>();
    this.commandHistory = new History();
    this.observers = new ArrayList<>();
    this.space = space;
    this.pen = new Pen();
    turtles.put(0, new TurtleSingle(0, 0, 0, 0, this.space));
    turtleStatus.put(0, true);
  }

/**
 * updates turtle location for every active turtle
 *
 * @param x double: represents x-coordinate
 * @param y double: represents y-coordinate
 **/
 @Override
  public void setLocation(double x, double y) {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).setLocation(x, y);
      this.notifyObservers();
    }
  }

  /**
   * updates turtle angle for every active turtle
   *
   * @param newAngle double: represents updated angle
   **/
  @Override
  public void updateAngle(double newAngle) {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).updateAngle(newAngle);
      this.notifyObservers();
    }
  }

  /**
   * adds command to history of turtle
   *
   * @param command Command: command to be added to History
   **/
  public void addHistory(Command command) {
    this.commandHistory.add(command);
  }

  /**
   * adds Observer to the list of History observers by calling History method
   *
   * @param observer Observer: observer to be added to History observer list
   **/
  public void addHistoryObserver(Observer observer) {
    this.commandHistory.register(observer);
  }

  /**
   * returns History of turtle
   **/
  public List<String> getHistory() {
    return this.commandHistory.getHistory();
  }

  /**
   * returns pen of turtle
   **/
  public Pen getPen() {
    return this.pen;
  }

  /**
   * registers observer as pen observer
   *
   * @param observer PenObserver: observer to be added to pen observer list
   **/
  public void registerPenObserver(PenObserver observer){
    this.pen.register(observer);
  }

  /**
   * registers observer as shape observer
   *
   * @param observer ShapeObserver: observer to be added to shape observer list
   **/
  public void registerShapeObserver(ShapeObserver observer){
    this.shape.register(observer);
  }

  /**
   * moves forward every active turtle
   *
   * @param distance double: distance to be moved
   **/
  public void goForward(double distance) {
    for (int id : this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).goForward(distance);
    }
    notifyObservers();
  }

  /**
   * moves backward every active turtle
   *
   * @param distance double: distance to be moved
   **/
  @Override
  public void goBackward(double distance) {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).goBackward(distance);
    }
    notifyObservers();
  }

  /**
   * calculates the distance moved by turtle
   * returns last distance moved calculation
   *
   * @param x double: new updated x location
   * @param y double: new updated y location
   **/
  @Override
  public double calcDistanceMoved(double x, double y) {
    Double result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) result = turtles.get(id).calcDistanceMoved(x, y);
    }
    return result;
  }

  /**
   * turns left every active turtle
   *
   * @param delta double: angle to be turned
   **/
  @Override
  public void turnLeft(double delta) {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).turnLeft(delta);
    }
    notifyObservers();
  }

  /**
   * turns right every active turtle
   *
   * @param delta double: angle to be moved
   **/
  @Override
  public void turnRight(double delta) {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).turnRight(delta);
    }
    notifyObservers();
  }

  /**
   * sets turtle towards point (x,y)
   *
   * @param x double: represents x-coordinate
   * @param y double: represents y-coordinate
   **/
  @Override
  public double setTowards(double x, double y) {
    Double result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) result = turtles.get(id).setTowards(x, y);
    }
    return result;
  }

  /**
   * calculates the angle turned by turtle
   * returns last angle turn calculation
   *
   * @param newAngle double: new updated angle
   **/
  @Override
  public double calcAngleTurned(double newAngle) {
    Double result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) result = turtles.get(id).calcAngleTurned(newAngle);
    }
    return result;
  }

  /**
   * returns TurtleSpace environment of turtle
   **/
  @Override
  public TurtleSpace getSpace() {
    return this.space;
  }

  /**
   * returns x-coordinate of turtle
   **/
  @Override
  public double getXCoordinate() {
    Double result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) result = turtles.get(id).getXCoordinate();
    }
    return result;
  }

  /**
   * returns y-coordinate of turtle
   **/
  @Override
  public double getYCoordinate() {
    Double result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) result = turtles.get(id).getYCoordinate();
    }
    return result;
  }

  /**
   * returns angle direction of turtle
   **/
  @Override
  public double getDirection() {
    Double result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).getDirection();
    }
    return result;
  }

  /**
   * returns display status of turtle
   **/
  @Override
  public boolean getStatus() {
    Boolean result = null;
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) result = turtles.get(id).getStatus();
    }
    return result;
  }

  /**
   * sets display status of turtle to true
   **/
  @Override
  public void show() {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).show();
    }
    notifyObservers();
  }

  /**
   * sets display status of turtle to false
   **/
  @Override
  public void hide() {
    for (int id: this.turtles.keySet()) {
      if (this.turtleStatus.get(id)) turtles.get(id).hide();
    }
    notifyObservers();
  }

  /**
   * overriden register method that allows object to receive updates on turtle
   *
   * @param observer TurtleObserver: observer to be added to observer list
   **/
  @Override
  public void register(TurtleObserver observer) {
    this.observers.add(observer);
  }

  /**
   * overriden remove method that allows object to be removed from list
   *
   * @param observer TurtleObserver: observer to be removed from observer list
   **/
  @Override
  public void remove(TurtleObserver observer) {
    this.observers.remove(observer);
  }

  /**
   * overriden notifyObservers method that sends updates to observers
   **/
  @Override
  public void notifyObservers() {
    for (TurtleObserver observer : observers ) {
      for (int id: this.turtles.keySet()) {
        if (this.turtleStatus.get(id)) {
          TurtleSingle t = this.turtles.get(id);
          observer.updateOnPosition(id, t.getXCoordinate(), t.getYCoordinate());
          observer.updateOnDirection(id, t.getDirection());
          observer.updateOnVisibility(id, t.getStatus());
        }
      }
    }
  }

  /**
   * returns number of turtles in workspace
   **/
  public double getTurtleCount() {
    return this.turtles.keySet().size();
  }

  /**
   * adds turtle to workspace if absent
   *
   * @param id int: id value of turtle to be added
   **/
  public void addTurtleIfAbsent(int id) {
    if (!this.turtles.containsKey(id)) {
      TurtleSingle turtle = new TurtleSingle(id, 0, 0, 0, this.space);
      this.turtles.put(id, turtle);
      this.turtleStatus.put(id, false);
    }
  }

  /**
   * activates turtles
   *
   * @param target: id values of turtle to be activated
   **/
  public void activateID(List<Integer> target) {
    for (int id : target) {
      addTurtleIfAbsent(id);
      this.turtleStatus.put(id, true);
    }
  }

  /**
   * deactivate turtles
   *
   * @param target: id values of turtle to be deactivated
   **/
  public void deactivateID(List<Integer> target) {
    for (int id : target) {
      this.turtleStatus.put(id, false);
    }
  }
}
