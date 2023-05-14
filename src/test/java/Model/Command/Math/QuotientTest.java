package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuotientTest {
  private Quotient command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "10.0", true));
    paramList.add(new Token("Constant", "3.0", true));
    command = new Quotient(paramList);
    double expected = 3.3333333333333335;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-5.0", true));
    paramList.add(new Token("Constant", "-3.0", true));
    command = new Quotient(paramList);
    double expected = 1.6666666666666667;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "-3.0", true));
    command = new Quotient(paramList);
    double expected = -0.0;
    assertEquals(expected, command.execute());
  }
}