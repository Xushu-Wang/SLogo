package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: PenUp Command that allows user to deactivate Pen to disable drawing as turtle moves
 * @author Jay Yoon
 *
 */
public class PenUp extends Command {

  public PenUp(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual PenUp command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    this.getTurtle().getPen().deactivate();
    return 0.0;
  }


}
