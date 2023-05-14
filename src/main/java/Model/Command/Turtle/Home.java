package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: Home Command that places turtle at center
 * @author Jay Yoon
 *
 */
public class Home extends Command {

  /**
   * Home command, reset the position of the turtle.
   * @param paramList
   */

  public Home(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Home command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    double change = getTurtle().calcDistanceMoved(0, 0);
    getTurtle().setLocation(0, 0);
    return change;
  }

}
