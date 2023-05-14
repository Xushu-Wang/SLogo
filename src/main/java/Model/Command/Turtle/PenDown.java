package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: PenDown Command that allows user to activate Pen to enable drawing as turtle moves
 * @author Jay Yoon
 *
 */
public class PenDown extends Command {

  public PenDown(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual PenDown command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    this.getTurtle().getPen().activate();
    return 0.0;
  }

}
