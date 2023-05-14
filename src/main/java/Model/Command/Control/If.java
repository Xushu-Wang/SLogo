package Model.Command.Control;

import Model.Interpreter;
import Model.TokenType.BodyToken;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
/**
 * @Purpose: If Control Structure Command that allows user to execute the command only if evaluation is true

 * @author Jay Yoon
 *
 */
public class If extends Command {
  private Interpreter internalParser;

  public If(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual If command
   *
   * @param0 Constant Token representing evaluation result of expr (0.0 false, else true)
   * @param1 Body Token that contains commands to be executed if evaluation is true
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalParser = new Interpreter(this.getTurtle().getSpace());

    Double result = 0.0;
    if (this.getParameter().get(0).getValue()!=0.0) {
      result = internalParser.processInput(this.getParameter().get(1).getContent(), this.getTurtle());
    }
    return result;
  }
}

