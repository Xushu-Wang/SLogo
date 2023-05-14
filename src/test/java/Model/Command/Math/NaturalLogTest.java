package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NaturalLogTest {
  private NaturalLog command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "2.0", true));
    command = new NaturalLog(paramList);
    double expected = 0.6931471805599453;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-30.0", true));
    command = new NaturalLog(paramList);
    assertNull(command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "1.0", true));
    command = new NaturalLog(paramList);
    double expected = 0;
    assertEquals(expected, command.execute());
  }

}