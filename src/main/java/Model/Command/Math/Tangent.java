package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Tangent extends Command {

  /**
   * Math.tan
   * @param paramList
   */

  public Tangent(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() throws ArithmeticException {
    double degree = getParameter().get(0).getValue();

    try {
      return Math.tan(Math.toRadians(degree));
    } catch (ArithmeticException e) {
      System.out.println(e);
      return null;
    }
  }
}
