package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Cosine extends Command {

  /**
   * Math.cos
   * @param paramList
   */
  public Cosine(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    double degree = getParameter().get(0).getValue();
    return Math.cos(Math.toRadians(degree));
  }
}
