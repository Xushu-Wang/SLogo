package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemainderTest {
  private Remainder command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "10.0", true));
    paramList.add(new Token("Constant", "3.0", true));
    command = new Remainder(paramList);
    double expected = 1.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-5.0", true));
    paramList.add(new Token("Constant", "-3.0", true));
    command = new Remainder(paramList);
    double expected = -2.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "-3.0", true));
    command = new Remainder(paramList);
    double expected = 0.0;
    assertEquals(expected, command.execute());
  }
}