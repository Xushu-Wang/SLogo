package Model.Command.Boolean;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EqualTest {
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void Equal() {
    setUp();
    paramList.add(new Token("Constant", "50.0", true));
    paramList.add(new Token("Constant", "50.0", true));
    Equal equal = new Equal(paramList);
    double result = equal.execute();

    assert(result == 1.0);
  }

  @Test
  void NotEqual() {
    setUp();
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "50.0", true));
    Equal equal = new Equal(paramList);
    double result = equal.execute();

    assert(result == 0.0);
  }

  @Test
  void Rounding() {
    setUp();
    paramList.add(new Token("Constant", "49.99999", true));
    paramList.add(new Token("Constant", "50.0", true));
    Equal equal = new Equal(paramList);
    double result = equal.execute();

    assert(result == 1.0);
  }


}