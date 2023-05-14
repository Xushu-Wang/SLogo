package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: SetPosition Command that allows user to set turtle position at specified point
 * @author Jay Yoon
 *
 */
public class SetPosition extends Command {

  public SetPosition(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual SetPosition command
   * @param0 Constant Token that represents the new x-coordinate of turtle
   * @param1 Constant Token that represents the new y-coordinate of turtle
   */
  @Override
  public Double execute() {
    double newX = this.getParameter().get(0).getValue();
    double newY = this.getParameter().get(1).getValue();
    double change = this.getTurtle().calcDistanceMoved(newX, newY);
    this.getTurtle().setLocation(newX, newY);

    return change;
  }

}
