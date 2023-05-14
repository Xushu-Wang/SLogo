package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArctangentTest {
  private Arctangent command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "0.5", true));
    command = new Arctangent(paramList);
    double expected = 26.56505117707799;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-60.0", true));
    command = new Arctangent(paramList);
    double expected = -89.04515874612783;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    command = new Arctangent(paramList);
    double expected = 0.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void One() {
    paramList.add(new Token("Constant", "1.0", true));
    command = new Arctangent(paramList);
    double expected = 45.0;
    assertEquals(expected, command.execute());
  }
}