package Model.Command.TurtleQuery;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: IsPenDown Command that queries turtle of pen status, whether it is enabled or disabled
 * @author Jay Yoon
 *
 */
public class IsPenDown extends Command {

  public IsPenDown(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual IsPenDown command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    return this.getTurtle().getPen().getStatus() ? 1.0 : 0.0;

  }

}
