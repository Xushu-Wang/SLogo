package Model.Command.Boolean;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class LessThan extends Command {

  /**
   * Boolean a < b
   * @param paramList
   */

  public LessThan(List<Token> paramList) {
    super(paramList);
  }

  public Double execute() {
    double expr1 = getParameter().get(0).getValue();
    double expr2 = getParameter().get(1).getValue();

    return expr1 < expr2 ? 1.0 : 0.0;
  }

}
