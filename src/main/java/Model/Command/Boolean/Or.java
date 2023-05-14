package Model.Command.Boolean;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Or extends Command {

  /**
   * Boolean a || b
   * @param paramList
   */

  public Or(List<Token> paramList) {
    super(paramList);
  }

  public Double execute() {
    double test1 = getParameter().get(0).getValue();
    double test2 = getParameter().get(1).getValue();

    return test1 != 0 || test2 != 0 ? 1.0 : 0.0;
  }

}
