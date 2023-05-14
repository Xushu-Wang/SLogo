package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class SquareRoot extends Command {

  /**
   * Math.sqrt
   * @param paramList
   */

  public SquareRoot(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double expr1 = getParameter().get(0).getValue();

    try {
      double sqrt = Math.sqrt(expr1);
      return sqrt;
    } catch (ArithmeticException e) {
      return null;
    }
  }

}
