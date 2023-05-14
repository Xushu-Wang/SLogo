package Model.Command.TurtleQuery;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: YCoordinate Command that queries turtle of y-coordinate position
 * @author Jay Yoon
 *
 */
public class YCoordinate extends Command {

  public YCoordinate(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual YCoordinate command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    return this.getTurtle().getYCoordinate();

  }

}
