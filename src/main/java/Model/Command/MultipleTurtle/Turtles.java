package Model.Command.MultipleTurtle;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
/**
 * @Purpose: Turtles Command that allows user to get the number of total turtles present on workspace
 * @author Jay Yoon
 *
 */
public class Turtles extends Command {

  public Turtles(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Turtles command
   * no parameter values are needed
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    return this.getTurtle().getTurtleCount();
  }
}
