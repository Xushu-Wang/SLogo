package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: Right Command that allows user to turn turtle rightwards
 * @author Jay Yoon
 *
 */
public class Right extends Command {

  /**
   * Right command, ask the turtle to rotate clockwise
   * @param paramList
   */

  public Right(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Right command
   * @param0 Constant Token that represents angle to be turned rightwards
   */
  @Override
  public Double execute() {
    double increment = this.getParameter().get(0).getValue();
    this.getTurtle().turnRight(increment);

    return increment;
  }

}
