package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;
import java.util.SplittableRandom;

public class Random extends Command {

  /**
   * Math.Random
   * @param paramList
   */

  public Random(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {
    SplittableRandom random = new SplittableRandom();
    return (double) random.nextInt(this.getParameter().get(0).getValue().intValue());
  }


}
