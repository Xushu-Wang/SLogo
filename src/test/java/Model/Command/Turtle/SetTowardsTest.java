package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.*;

import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetTowardsTest {
  private Turtle turtle;
  private ArrayList<Token> paramList;
  private SetTowards st;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicHeading() {
    turtle = new Turtle(space);
    st = new SetTowards(paramList);
    st.attach(turtle);
    double expected = 90.0;
    st.execute();
    double result = turtle.getDirection();
    assertEquals(expected, result);
  }

}