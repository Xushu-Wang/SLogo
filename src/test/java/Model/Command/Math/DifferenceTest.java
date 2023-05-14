package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DifferenceTest {
  private Difference command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "10.0", true));
    paramList.add(new Token("Constant", "20.0", true));
    command = new Difference(paramList);
    double expected = -10.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-10.3", true));
    paramList.add(new Token("Constant", "-5.0", true));
    command = new Difference(paramList);
    double expected = -5.300000000000001;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "0.0", true));
    command = new Difference(paramList);
    double expected = 0.0;
    assertEquals(expected, command.execute());
  }
}