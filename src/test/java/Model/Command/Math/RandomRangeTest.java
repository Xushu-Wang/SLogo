package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Constant;
import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomRangeTest {
  private RandomRange random;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void randomRange() {
    paramList.add(new Constant("Constant", "0"));
    paramList.add(new Constant("Constant", "2"));
    random = new RandomRange(paramList);
    boolean lowerBound = (random.execute() >= paramList.get(0).getValue());
    boolean upperBound = (random.execute() < paramList.get(1).getValue());
    assertTrue(lowerBound && upperBound);
  }

}