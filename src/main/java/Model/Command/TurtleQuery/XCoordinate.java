package Model.Command.TurtleQuery;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: XCoordinate Command that queries turtle of x-coordinate position
 * @author Jay Yoon
 *
 */
public class XCoordinate extends Command {

  public XCoordinate(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual XCoordinate command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    return this.getTurtle().getXCoordinate();

  }


}
