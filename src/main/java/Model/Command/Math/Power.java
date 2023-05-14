package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Power extends Command {

  /**
   * Math. a^b
   * @param paramList
   */

  public Power(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double base = getParameter().get(0).getValue();
    double exponent = getParameter().get(1).getValue();

    return Math.pow(base, exponent);
  }

}
