package Model.Command.Boolean;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class GreaterThan extends Command {

  /**
   * Boolean a > b
   * @param paramList
   */

  public GreaterThan(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double expr1 = getParameter().get(0).getValue();
    double expr2 = getParameter().get(1).getValue();

    return expr1 > expr2 ? 1.0 : 0.0;
  }

}
