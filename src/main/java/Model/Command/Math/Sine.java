package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Sine extends Command {

  /**
   * Math.sin
   * @param paramList
   */

  public Sine(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double degree = getParameter().get(0).getValue();
    return Math.sin(Math.toRadians(degree));
  }
}
