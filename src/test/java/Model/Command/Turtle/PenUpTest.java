package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.assertFalse;

import Model.Command.Turtle.PenUp;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PenUpTest {

  private Turtle turtle;
  private ArrayList<Token> paramList;
  private PenUp penUp;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicPenUp() {
    turtle = new Turtle(space);
    penUp = new PenUp(paramList);
    penUp.attach(turtle);
    penUp.execute();
    assertFalse(turtle.getPen().getStatus());
  }

}