package Model.Command.TurtleQuery;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class XCoordinateTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private XCoordinate xCoordinate;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicQuery() {
    turtle = new Turtle(space);
    xCoordinate = new XCoordinate(paramList);
    xCoordinate.attach(turtle);
    double expected = 60;
    double result = xCoordinate.execute();
    assertEquals(expected, result);
  }

  @Test
  void basicNegativeCoordinate() {
    turtle = new Turtle(space);
    xCoordinate = new XCoordinate(paramList);
    xCoordinate.attach(turtle);
    double expected = -100;
    double result = xCoordinate.execute();
    assertEquals(expected, result);
  }

  @Test
  void basicDoubleCoordinate() {
    turtle = new Turtle(space);
    xCoordinate = new XCoordinate(paramList);
    xCoordinate.attach(turtle);
    double expected = -6.89;
    double result = xCoordinate.execute();
    assertEquals(expected, result);
  }

}