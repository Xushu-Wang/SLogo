package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShowTurtleTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private ShowTurtle sh;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicHeading() {
    turtle = new Turtle(space);
    sh = new ShowTurtle(paramList);
    sh.attach(turtle);
    sh.execute();
    boolean result = turtle.getStatus();
    assertTrue(result);
  }

}