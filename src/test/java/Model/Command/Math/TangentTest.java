package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TangentTest {
  private Tangent command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "30.0", true));
    command = new Tangent(paramList);

    double expected = 0.5773502691896257;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "180.0", true));
    command = new Tangent(paramList);
    double expected = -1.2246467991473532E-16;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "45.0", true));
    command = new Tangent(paramList);
    double expected = 0.9999999999999999;
    assertEquals(expected, command.execute());
  }

  @Test
  void One() {
    paramList.add(new Token("Constant", "90.0", true));
    command = new Tangent(paramList);
    assertEquals(1.633123935319537E16, command.execute());
  }
}