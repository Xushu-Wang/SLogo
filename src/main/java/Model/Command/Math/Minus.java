package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Minus extends Command {

  /**
   * Math.negative(-a)
   * @param paramList
   */

  public Minus(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double expr1 = getParameter().get(0).getValue();
    return 0.0 - expr1;
  }

}
