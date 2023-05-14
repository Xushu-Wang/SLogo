package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetPositionTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private SetPosition sp;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicX() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "90.0"));
    paramList.add(new Constant("Constant", "90.0"));
    sp = new SetPosition(paramList);
    sp.attach(turtle);
    double expected = 90.0;
    sp.execute();
    double result = turtle.getXCoordinate();
    assertEquals(expected, result);
  }

  void basicY() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "90.0"));
    paramList.add(new Constant("Constant", "90.0"));
    sp = new SetPosition(paramList);
    sp.attach(turtle);
    double expected = 90.0;
    sp.execute();
    double result = turtle.getYCoordinate();
    assertEquals(expected, result);
  }

}