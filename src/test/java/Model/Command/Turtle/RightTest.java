package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.Command.Turtle.Right;
import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RightTest {

  private Turtle turtle;
  private ArrayList<Token> paramList;
  private Right rt;
  private TurtleSpace space;


  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicRight() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "90.0"));
    rt = new Right(paramList);
    rt.attach(turtle);
    double expected = 180.0;
    rt.execute();
    double result = turtle.getDirection();
    assertEquals(expected, result);
  }

  @Test
  void overloadRight() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "60.0"));
    rt = new Right(paramList);
    rt.attach(turtle);
    double expected = 300.0;
    rt.execute();
    double result = turtle.getDirection();
    assertEquals(expected, result);
  }
}