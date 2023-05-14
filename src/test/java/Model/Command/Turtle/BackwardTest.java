package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Model.Command.Turtle.Backward;
import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BackwardTest {

  private Turtle turtle;
  private ArrayList<Token> paramList;
  private Backward bk;

  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicBackward() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "-5.0"));
    bk = new Backward(paramList);
    bk.attach(turtle);
    bk.execute();
    double[] expected = new double[]{-5.0, 0.0};
    double[] result = new double[]{turtle.getXCoordinate(), turtle.getYCoordinate()};
    assertArrayEquals(expected, result);
  }

  @Test
  void negativeForward() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "-5.0"));
    bk = new Backward(paramList);
    bk.attach(turtle);
    bk.execute();
    double[] expected = new double[]{5.0, 0.0};
    double[] result = new double[]{turtle.getXCoordinate(), turtle.getYCoordinate()};
    assertArrayEquals(expected, result);
  }
}
