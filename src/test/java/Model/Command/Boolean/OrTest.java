package Model.Command.Boolean;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrTest {
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void BothNonZero() {
    setUp();
    paramList.add(new Token("Constant", "50.0", true));
    paramList.add(new Token("Constant", "50.0", true));
    Or or = new Or(paramList);
    double result = or.execute();

    assert(result == 1.0);
  }

  @Test
  void OneNonZero() {
    setUp();
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "50.0", true));
    Or or = new Or(paramList);
    double result = or.execute();

    assert(result == 1.0);
  }

  @Test
  void SecondNonZero() {
    setUp();
    paramList.add(new Token("Constant", "50.0", true));
    paramList.add(new Token("Constant", "0.0", true));
    Or or = new Or(paramList);
    double result = or.execute();

    assert(result == 1.0);
  }

  @Test
  void AllZero() {
    setUp();
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "0.0", true));
    Or or = new Or(paramList);
    double result = or.execute();

    assert(result == 0.0);
  }

}