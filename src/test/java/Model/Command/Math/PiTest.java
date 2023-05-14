package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.Command.Boolean.Equal;
import Model.TokenType.Token;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PiTest {
  private Pi command;
  private ArrayList<Token> paramList;
  private Equal equal;
  private double expected;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    command = new Pi(paramList);
    expected = 3.141592653589793;
    assertEquals(command.execute(), expected);
  }

}