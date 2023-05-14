package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class NaturalLog extends Command {

  /**
   * Math.log
   * @param paramList
   */

  public NaturalLog(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double expr1 = getParameter().get(0).getValue();

    try {
      double log = Math.log(expr1);
      return log;
    } catch (ArithmeticException e) {
      System.out.println(e);
      return null;
    }
  }

}
