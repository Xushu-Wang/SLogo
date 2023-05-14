package Model.Command.Math;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
  private Product command;
  private ArrayList<Token> paramList;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basic() {
    paramList.add(new Token("Constant", "10.0", true));
    paramList.add(new Token("Constant", "15.3", true));
    command = new Product(paramList);
    double expected = 153.0;
    assertEquals(expected, command.execute());
  }

  @Test
  void negative() {
    paramList.add(new Token("Constant", "-15.0", true));
    paramList.add(new Token("Constant", "-12.1", true));
    command = new Product(paramList);
    double expected = 181.5;
    assertEquals(expected, command.execute());
  }

  void mix(){
    paramList.add(new Token("Constant", "15.0", true));
    paramList.add(new Token("Constant", "-3.1", true));
    command = new Product(paramList);
    double expected = -46.5;
    assertEquals(expected, command.execute());
  }

  @Test
  void zero() {
    paramList.add(new Token("Constant", "0.0", true));
    paramList.add(new Token("Constant", "15112.3", true));
    command = new Product(paramList);
    double expected = 0.0;
    assertEquals(expected, command.execute());
  }
}