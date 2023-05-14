package Model.Command.Boolean;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class And extends Command {

  /**
   * Boolean a && b
   * @param paramList
   */

  public And(List<Token> paramList) {
    super(paramList);
  }

  public Double execute() {
    double first = getParameter().get(0).getValue();
    double second = getParameter().get(1).getValue();

    return first != 0 && second != 0 ? 1.0 : 0.0;
  }

}
