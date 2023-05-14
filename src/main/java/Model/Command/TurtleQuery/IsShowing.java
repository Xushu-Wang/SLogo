package Model.Command.TurtleQuery;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: IsShowing Command that queries turtle of display status, whether turtle is being shown in window
 * @author Jay Yoon
 *
 */
public class IsShowing extends Command {

  public IsShowing(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual IsShowing command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    return this.getTurtle().getStatus() ? 1.0 : 0.0;

  }


}
