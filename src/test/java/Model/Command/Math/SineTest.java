package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.Command.Boolean.Equal;
import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SineTest {
  private Sine command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "30.0", true));
    command = new Sine(paramList);

    double expected = 0.49999999999999994;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-30.0", true));
    command = new Sine(paramList);
    double expected = -0.49999999999999994;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    command = new Sine(paramList);
    double expected = 0.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void One() {
    paramList.add(new Token("Constant", "90.0", true));
    command = new Sine(paramList);
    double expected = 1.0;
    assertEquals(expected, command.execute());
  }
}