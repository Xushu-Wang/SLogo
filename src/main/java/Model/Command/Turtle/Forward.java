package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;
/**
 * @Purpose: Forward Command that allows user to move turtle backwards by specified distance
 * @author Jay Yoon
 *
 */
public class Forward extends Command {

  /**
   * Forward command, ask the turtle to move towards its direction
   * @param paramList
   */

  public Forward(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Forward command
   *
   * @param0 Constant Token that represents distance to be moved forwards
   */
  @Override
  public Double execute() {
    double increment = getParameter().get(0).getValue();
    this.getTurtle().goForward(increment);
    return increment;
  }

}
