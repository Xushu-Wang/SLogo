package Model.Command.Boolean;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotTest {
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void NonZero() {
    setUp();
    paramList.add(new Token("Constant", "50.0", true));
    Not not = new Not(paramList);
    double result = not.execute();

    assert(result == 0.0);
  }

  @Test
  void Zero() {
    setUp();
    paramList.add(new Token("Constant", "0.0", true));
    Not not = new Not(paramList);
    double result = not.execute();

    assert(result == 1.0);
  }

}