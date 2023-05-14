package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: SetHeading Command that allows user to set turtle direction to certain angle
 * @author Jay Yoon
 *
 */
public class SetHeading extends Command {

  public SetHeading(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual SetHeading command
   * @param0 Constant Token that represents the new angle of turtle direction
   */
  @Override
  public Double execute() {
    double newAngle = this.getParameter().get(0).getValue();
    double change = this.getTurtle().calcAngleTurned(newAngle);
    this.getTurtle().updateAngle(newAngle);

    return change;
  }

}
