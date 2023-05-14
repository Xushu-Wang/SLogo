package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: Left Command that allows user to turn turtle leftwards
 * @author Jay Yoon
 *
 */
public class Left extends Command {

  /**
   * Left command, ask the turtle to rotate counterclockwise.
   * @param paramList
   */

  public Left(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Left command
   * @param0 Constant Token that represents angle to be turned leftwards
   */
  @Override
  public Double execute() {
    double increment = this.getParameter().get(0).getValue();
    this.getTurtle().turnLeft(increment);

    return increment;
  }

}
