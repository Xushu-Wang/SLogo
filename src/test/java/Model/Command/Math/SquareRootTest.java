package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareRootTest {
  private SquareRoot command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "10.0", true));
    command = new SquareRoot(paramList);
    double expected = Math.sqrt(10.0);
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-5.0", true));
    command = new SquareRoot(paramList);
    assertNull(command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    command = new SquareRoot(paramList);
    double expected = 0.0;
    assertEquals(expected, command.execute());
  }
}