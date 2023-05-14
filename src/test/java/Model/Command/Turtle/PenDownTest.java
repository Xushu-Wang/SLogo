package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PenDownTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private PenDown penDown;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");

  }

  @Test
  void basicPenUp() {
    turtle = new Turtle(space);
    penDown = new PenDown(paramList);
    penDown.attach(turtle);
    penDown.execute();
    assertTrue(turtle.getPen().getStatus());
  }

}