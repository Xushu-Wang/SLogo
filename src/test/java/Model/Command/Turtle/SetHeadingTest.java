package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetHeadingTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private SetHeading sh;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicHeading() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "90.0"));
    sh = new SetHeading(paramList);
    sh.attach(turtle);
    double expected = 90.0;
    sh.execute();
    double result = turtle.getDirection();
    assertEquals(expected, result);
  }



}