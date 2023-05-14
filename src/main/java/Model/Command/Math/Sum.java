package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class Sum extends Command {

  /**
   * Math.a+b
   * @param paramList
   */

  public Sum(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    Double termOne = this.getParameter().get(0).getValue();
    Double termTwo = this.getParameter().get(1).getValue();

    return termOne + termTwo;
  }
}
