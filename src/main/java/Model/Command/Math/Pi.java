package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Pi extends Command {

  /**
   * Math.Pi
   * @param paramList
   */

  public Pi(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    return Math.PI;
  }

}
