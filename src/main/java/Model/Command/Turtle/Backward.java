package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;
/**
 * @Purpose: Backward Command that allows user to move turtle backwards by specified distance
 * @author Jay Yoon
 *
 */
public class Backward extends Command {

  /**
   * Backward Command, ask the turtle to move oppositely towards its direction
   * @param paramList
   */

  public Backward(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Backward command
   *
   * @param0 Constant Token that represents distance to be moved backwards
   */
  @Override
  public Double execute() {

    double increment = getParameter().get(0).getValue();
    this.getTurtle().goBackward(increment);

    return increment;
  }
}
