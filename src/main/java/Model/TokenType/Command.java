package Model.TokenType;

import Model.Turtle;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Purpose: Command Token that holds String content inside brackets
 * Used in Loops, Control Structures, Custom User Instructions that require square brackets
 * @author Jay Yoon
 *
 */
public abstract class Command extends Function {

  private List<Token> paramList;
  private Turtle turtle;
  private int paramReq;

  public Command(List<Token> paramList) {
    super("Command", "1.0");
    this.paramList = paramList;
  }

  /**
   * executes the command
   * returns Double value that is returned from execution
   */
  public abstract Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException;

  /**
   * returns List of Tokens that represents parameter of function
   */
  public List<Token> getParameter() {
    return paramList;
  }

  /**
   * updates the List of Tokens that represents parameter of function
   */
  public void setParameter(List<Token> param) {
    this.paramList = param;
  }

  /**
   * attaches the turtle to the command
   */
  public void attach(Turtle t) {
    this.turtle = t;
  }

  /**
   * returns number of required parameters
   */
  public int getParamReq() { return this.paramReq; }

  /**
   * updates number of required parameters in construction of command
   */
  public void setParamReq(int num) {
    this.paramReq = num;
  }

  /**
   * returns String representation of specific command
   * this is used in displaying history of commands
   */
  @Override
  public String toString() {
    List<String> list = new ArrayList<>();
    for (Token token : paramList) list.add(token.getContent());
    String[] packagesInClassName = this.getClass().toString().split(PERIOD);
    String className = packagesInClassName[packagesInClassName.length - 1];
    return  className+SINGLE_SPACE+ String.join(SINGLE_SPACE, list);
  }

  /**
   * returns turtle that is attached to command
   */
  public Turtle getTurtle(){
    return this.turtle;
  }
}
