package Model.Command.Math;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;
import java.util.Random;

public class RandomRange extends Command {

  /**
   * Math.Random for a given range
   */

  private final Random random;

  public RandomRange(List<Token> paramList) {
    super(paramList);
    random = new Random();
  }

  @Override
  public Double execute() {
    double min = getParameter().get(0).getValue();
    double max = getParameter().get(1).getValue();

    return random.nextDouble(min, max);
  }
}
