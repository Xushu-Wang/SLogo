package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.Command.Math.Sum;
import Model.TokenType.Constant;
import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SumTest {

  private Sum sumCommand;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basicSum() {
    paramList.add(new Constant("Constant", "1.0"));
    paramList.add(new Constant("Constant", "50.0"));
    sumCommand = new Sum(paramList);
    double expected = 51.0;
    assertEquals(expected, sumCommand.execute());
  }

  @Test
  void negativeSum() {
    paramList.add(new Constant("Constant", "-5.0"));
    paramList.add(new Constant("Constant", "10.0"));
    sumCommand = new Sum(paramList);
    double expected = 5.0;
    assertEquals(expected, sumCommand.execute());
  }

  @Test
  void zeroSum() {
    paramList.add(new Constant("Constant", "0.0"));
    paramList.add(new Constant("Constant", "10.0"));
    sumCommand = new Sum(paramList);
    double expected = 10.0;
    assertEquals(expected, sumCommand.execute());
  }
}