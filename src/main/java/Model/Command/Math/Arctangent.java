package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Arctangent extends Command {

  /**
   * Math.arctan
   * @param paramList
   */

  public Arctangent(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double value = getParameter().get(0).getValue();

    return Math.toDegrees(Math.atan(value));
  }

}
