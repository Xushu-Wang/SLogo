package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CosineTest {
  private Cosine command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "60.0", true));
    command = new Cosine(paramList);
    double expected = 0.5000000000000001;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-60.0", true));
    command = new Cosine(paramList);
    double expected = 0.5000000000000001;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    command = new Cosine(paramList);
    double expected = 1.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void One() {
    paramList.add(new Token("Constant", "90.0", true));
    command = new Cosine(paramList);
    double expected = 6.123233995736766E-17;
    assertEquals(expected, command.execute());
  }
}