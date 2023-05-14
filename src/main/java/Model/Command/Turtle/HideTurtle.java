package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;
/**
 * @Purpose: Hide Command that allows user to hide turtle and not be shown on window
 * @author Jay Yoon
 *
 */
public class HideTurtle extends Command {

  /**
   * HideTurtle command, hide the turtle from the user
   * @param paramList
   */

  public HideTurtle(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Hide command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    this.getTurtle().hide();
    return 0.0;
  }

}
