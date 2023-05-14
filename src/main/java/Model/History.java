package Model;

import Controller.Observer;
import Controller.Observable;
import Model.TokenType.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Purpose: History class that represents stack of commands run in window, to be shown in History tab of display
 * @author Jay Yoon, Rodrigo Guerreiro
 *
 */
public class History implements Observable<Observer> {
  private final List<Observer> myObservers;
  private final Stack<Command> commands;

  public History() {
    commands = new Stack<>();
    myObservers = new ArrayList<>();
  }

  /**
   * adds Command to stack of past commands and notifies its observers
   * @param command Command: executed command to be added
   */
  public void add(Command command) {
    this.commands.add(command);
    this.notifyObservers();
  }

  /**
   * gets List of commands as String values to be accessed by user
   */
  public List<String> getHistory() {
    List<String> listOfCommands = new ArrayList<>();
    for (Command command : commands) {
      listOfCommands.add(command.toString());
    }
    return listOfCommands;
  }

  /**
   * overriden register method from Observable interface
   * enables Observer to register as History observer to receive updates by adding to list of observers
   * @param observer Observer: observer object to be added
   */
  @Override
  public void register(Observer observer) {
    myObservers.add(observer);
  }

  /**
   * overriden remove method from Observable interface
   * enables Observer to remove observer from list of observers
   * @param observer Observer: observer object to be removed
   */
  @Override
  public void remove(Observer observer) {
    myObservers.remove(observer);
  }

  /**
   * overriden notifyObservers method from Observable interface
   * enables Observer to notify observers from list of observers
   */
  @Override
  public void notifyObservers() {
    for (Observer observer : myObservers) {
      observer.onUpdate("History", this.commands.peek().toString());
    }
  }
}
