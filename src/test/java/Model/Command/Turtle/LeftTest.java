package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.Command.Turtle.Left;
import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeftTest {

  private Turtle turtle;
  private ArrayList<Token> paramList;
  private Left lt;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicLeft() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "60.0"));
    lt = new Left(paramList);
    lt.attach(turtle);
    double expected = 60.0;
    lt.execute();
    double result = turtle.getDirection();
    assertEquals(expected, result);
  }

  @Test
  void overloadLeft() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "40.0"));
    lt = new Left(paramList);
    lt.attach(turtle);
    double expected = 20.0;
    lt.execute();
    double result = turtle.getDirection();
    assertEquals(expected, result);
  }
}