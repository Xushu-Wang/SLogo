package Model.Command.TurtleQuery;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IsPenDownTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private IsPenDown isPenDown;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicQuery() {
    turtle = new Turtle(space);
    isPenDown = new IsPenDown(paramList);
    isPenDown.attach(turtle);
    turtle.getPen().deactivate();
    double expected = 0;
    double result = isPenDown.execute();
    assertEquals(expected, result);
  }

}