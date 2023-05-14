package Model.Command.Boolean;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Equal extends Command {

  /**
   * Boolean. a == b
   * @param paramList
   */

  public Equal(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double expr1 = getParameter().get(0).getValue();
    double expr2 = getParameter().get(1).getValue();

    return Math.abs(expr1 - expr2) < 0.001 ? 1.0 : 0.0;
  }

}
