package Model.Command.TurtleQuery;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class YCoordinateTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private YCoordinate yCoordinate;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicQuery() {
    turtle = new Turtle(space);
    yCoordinate = new YCoordinate(paramList);
    yCoordinate.attach(turtle);
    double expected = 30;
    double result = yCoordinate.execute();
    assertEquals(expected, result);
  }

  @Test
  void basicNegativeCoordinate() {
    turtle = new Turtle(space);
    yCoordinate = new YCoordinate(paramList);
    yCoordinate.attach(turtle);
    double expected = -10;
    double result = yCoordinate.execute();
    assertEquals(expected, result);
  }

  @Test
  void basicDoubleCoordinate() {
    turtle = new Turtle(space);
    yCoordinate = new YCoordinate(paramList);
    yCoordinate.attach(turtle);
    double expected = 30.49;
    double result = yCoordinate.execute();
    assertEquals(expected, result);
  }

}