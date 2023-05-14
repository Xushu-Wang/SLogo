package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Quotient extends Command {

  /**
   * Math. a/b
   * @param paramList
   */

  public Quotient(List<Token> paramList) {
    super(paramList);
  }

  public Double execute() {
    double expr1 = getParameter().get(0).getValue();
    double expr2 = getParameter().get(1).getValue();

    return expr1 / expr2;
  }

}
