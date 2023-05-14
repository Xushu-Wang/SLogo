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
 * @Purpose: IfElse Control Structure Command that allows user to execute different commands depending on evaluation result

 * @author Jay Yoon
 *
 */
public class IfElse extends Command {
  private Interpreter internalParser;

  public IfElse(List<Token> paramList) {
    super(paramList);
  }

  //param 0: 1.0 or 0.0 (evaluated beforehand) Constant
  //param 1: groupToken of commands (true)
  //param 2: groupToken of commands (false)
  /**
   * execution of actual IfElse command
   *
   * @param0 Constant Token representing evaluation result of expr (0.0 false, else true)
   * @param1 Body Token that contains commands to be executed if evaluation is true
   * @param2 Body Token that contains commands to be executed if evaluation is false
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalParser = new Interpreter(this.getTurtle().getSpace());

    Double result = 0.0;
    if (this.getParameter().get(0).getValue()!=0.0) {
      result = internalParser.processInput(this.getParameter().get(1).getContent(), this.getTurtle());
    } else {
      result = internalParser.processInput(this.getParameter().get(2).getContent(), this.getTurtle());
    }
    return result;
  }
}
