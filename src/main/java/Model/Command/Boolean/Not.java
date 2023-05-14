package Model.Command.Boolean;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Not extends Command {

  /**
   * Boolean a != 0
   * @param paramList
   */

  public Not(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double test1 = getParameter().get(0).getValue();

    return test1 == 0 ? 1.0 : 0.0;
  }

}
