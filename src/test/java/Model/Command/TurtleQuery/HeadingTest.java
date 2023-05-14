package Model.Command.TurtleQuery;

import static org.junit.jupiter.api.Assertions.*;

import Model.Command.Turtle.Forward;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeadingTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private Heading heading;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicQuery() {
    turtle = new Turtle(space);
    heading = new Heading(paramList);
    heading.attach(turtle);
    double expected = 70;
    double result = heading.execute();
    assertEquals(expected, result);
  }

}