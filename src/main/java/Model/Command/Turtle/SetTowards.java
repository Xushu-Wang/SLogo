package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: SetTowards Command that allows user to set turtle angle facing certain point
 * @author Jay Yoon
 *
 */
public class SetTowards extends Command {

  public SetTowards(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual SetTowards command
   * @param0 Constant Token that represents the x-coordinate to be faced towards
   * @param1 Constant Token that represents the y-coordinate to be faced towards
   */
  @Override
  public Double execute() {
    double x = this.getParameter().get(0).getValue();
    double y = this.getParameter().get(1).getValue();
    double newAngle = this.getTurtle().setTowards(x, y);
    double change = this.getTurtle().calcAngleTurned(newAngle);
    this.getTurtle().updateAngle(newAngle);

    return change;
  }

}
