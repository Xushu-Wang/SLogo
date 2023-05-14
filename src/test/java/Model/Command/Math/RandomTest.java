package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.assertTrue;

import Model.Command.Math.Random;
import Model.TokenType.Constant;
import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomTest {

  private Random random;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void randomRange() {
    paramList.add(new Constant("Constant", "50"));
    random = new Random(paramList);
    boolean lowerBound = (random.execute() >= 0);
    boolean upperBound = (random.execute() < paramList.get(0).getValue());
    assertTrue(lowerBound && upperBound);
  }

}