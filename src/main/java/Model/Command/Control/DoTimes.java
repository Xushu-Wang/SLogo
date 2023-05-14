package Model.Command.Control;

import Model.Interpreter;
import Model.TokenType.BodyToken;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import Model.TokenType.Variable;
import Model.Turtle;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
/**
 * @Purpose: Loop Structure Command that allows user to execute given commands multiple times
 * The user can give the variable and expr directly, but can also give expr as a command to be evaluated
 * @author Jay Yoon
 *
 */
public class DoTimes extends Command {
  private TokenFactory internalTF;
  private Interpreter internalParser;

  /**
   * DoTimes operation, paramList include the Group Tokens and limit of dotimes.
   * @param paramList
   */

  public DoTimes(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual doTimes command
   *
   * @param0 Body Token that contains information of variable to be indexed and its limit (var, expr)
   * @param1 Body Token that contains commands to be executed multiple times
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalTF = new TokenFactory(this.getTurtle().getSpace().getLanguage(), this.getTurtle().getSpace());
    internalParser = new Interpreter(this.getTurtle().getSpace());

    String[] control = this.getParameter().get(0).getContent().split(WHITESPACE);
    String subCommand = EMPTY;
    double limit;
    Variable var = (Variable) internalTF.generate(VARIABLE_TYPE, control[0]);
    for (int i=1; i<control.length; i++) {
      subCommand += control[i]+SINGLE_SPACE;
    }
    if (control.length>2) {
      limit = internalParser.processInput(subCommand, this.getTurtle());
    }
    else {
      limit = Integer.parseInt(String.valueOf(control[1]));
    }

    Double result = null;
    for (int i=0; i<limit; i++) {
      result = internalParser.processInput(this.getParameter().get(1).getContent(), this.getTurtle());
      var.setValue((double)i);
    }
    return result;
  }
}
