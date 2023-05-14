package Model;

import Controller.BackgroundObserver;
import Controller.Observable;
import Controller.Observer;
import Model.Command.UserDefined.UserCommand;
import Model.TokenType.Token;
import Model.TokenType.Variable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Purpose: TurtleSpace class that represents a workspace
 * Enables separation of workspaces in different windows
 * Enables retrieval of user-saved variables and functions with custom language option
 * @author Jay Yoon
 *
 */
public class TurtleSpace {

  private Background background;
  private String language;
  private final List<Variable> variables;
  private final List<UserCommand> userCommands;
  public static final String END_MARKER = "END";

  public TurtleSpace(String language) {
    this.language = language;
    this.variables = new ArrayList<>();
    this.userCommands = new ArrayList<>();
    background = new Background();
  }

  public boolean containsKey(String varName) {
    for (Variable var : variables) {
      if (var.getType().equals(varName)) return true;
    }
    return false;
  }

  public Variable getVariable(String varName) {
    for (Variable variable : variables) {
      if (variable.getType().equals(varName))
        return variable;
    }
    return null;
  }

  public boolean containsCommand(String keyword) {
    for (UserCommand c : userCommands) {
      if (c.getName().equals(keyword)) return true;
    }
    return false;
  }

  public Token getUserCommand(String keyword) {
    for (UserCommand c : userCommands) {
      if (c.getName().equals(keyword)) return c;
    }
    return null;
  }

  public void registerBackgroundObserver(BackgroundObserver b){
    background.register(b);
  }

  public void removeBackgroundObserver(BackgroundObserver b){
    background.remove(b);
  }

  public Background getBackground(){
    return background;
  }

  public void addCommand(UserCommand userCommand) {
    this.userCommands.add(userCommand);
  }

  public void addVariable(Variable var) {
    this.variables.add(var);
  }

  public List<Variable> getVariables() {
    return Collections.unmodifiableList(variables);
  }

  public List<UserCommand> getUserCommands() {
    return Collections.unmodifiableList(userCommands);
  }
  public String getLanguage() { return this.language; }

  public String variablesAsString() {
    StringBuilder variableStrings = new StringBuilder();
    for (Variable variable : this.variables) {
      variableStrings.append(variable.toString());
      variableStrings.append("\n");
    }
    return variableStrings.toString();
  }

  public String functionsAsString() {
    StringBuilder commandStrings = new StringBuilder();
    for (UserCommand command : this.userCommands) {
      commandStrings.append(command.getName());
      commandStrings.append(END_MARKER);
    }
    return commandStrings.toString();
  }
}
