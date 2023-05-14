package Model.Command.TurtleQuery;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: Heading Command that queries turtle of its direction angle
 * @author Jay Yoon
 *
 */
public class Heading extends Command {

  public Heading(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Heading command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    return this.getTurtle().getDirection();

  }

}
