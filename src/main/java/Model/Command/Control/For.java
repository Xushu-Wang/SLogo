package Model.Command.Control;

import Model.Interpreter;
import Model.TokenType.BodyToken;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import Model.TokenType.Variable;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
/**
 * @Purpose: For-Loop Structure Command that allows user to execute given commands multiple times

 * @author Jay Yoon
 *
 */
public class For extends Command {
  private TokenFactory internalTF;
  private Interpreter internalParser;

  /**
   * For Control Loop, paramList includes expression that needs to be evaluated and group tokens to be executed
   * @param paramList
   */

  public For(List<Token> paramList) {
    super(paramList);
  }

  //0 -> Group Token content: ":t 1 360 5"
  //1 -> Group Token content: "rt / sin :t 2"
  /**
   * execution of actual For command
   *
   * @param0 Body Token that contains information of variable to be indexed with starting value, its limit, and increment
   * @param1 Body Token that contains commands to be executed multiple times
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalTF = new TokenFactory(this.getTurtle().getSpace().getLanguage(), this.getTurtle().getSpace());
    internalParser = new Interpreter(this.getTurtle().getSpace());

    String[] control = this.getParameter().get(0).getContent().split(SINGLE_SPACE);
    Variable index = (Variable) internalTF.generate(VARIABLE_TYPE, control[0]);
    double start = Double.parseDouble(control[1]);
    double end = Double.parseDouble(control[2]);
    double increment = Double.parseDouble(control[3]);
    index.setValue(start);

    Double result = null;
    for (int i= (int) start; i<=end; i+=increment) {
      result = internalParser.processInput(this.getParameter().get(1).getContent(), this.getTurtle());
      index.setValue((double) i);
    }
    return result;
  }
}
