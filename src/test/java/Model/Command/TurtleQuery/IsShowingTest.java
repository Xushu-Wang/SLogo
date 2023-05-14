package Model.Command.TurtleQuery;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IsShowingTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private IsShowing isShowing;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
  }

  @Test
  void basicQuery() {
    turtle = new Turtle(space);
    isShowing = new IsShowing(paramList);
    isShowing.attach(turtle);
    turtle.show();
    double expected = 1;
    double result = isShowing.execute();
    assertEquals(expected, result);
  }

  @Test
  void basicNegationQuery() {
    turtle = new Turtle(space);
    isShowing = new IsShowing(paramList);
    isShowing.attach(turtle);
    turtle.hide();
    double expected = 0;
    double result = isShowing.execute();
    assertEquals(expected, result);
  }



}