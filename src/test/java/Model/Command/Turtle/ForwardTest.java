package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Model.Command.Turtle.Forward;
import Model.TokenType.Constant;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ForwardTest {

  private Turtle turtle;
  private ArrayList<Token> paramList;
  private Forward fd;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    space = new TurtleSpace("English");
    paramList = new ArrayList<>();
  }

  @Test
  void basicForward() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "5.0"));
    fd = new Forward(paramList);
    fd.attach(turtle);
    fd.execute();
    double[] expected = new double[]{0.0, -5.0};
    double[] result = new double[]{turtle.getXCoordinate(), turtle.getYCoordinate()};
    assertArrayEquals(expected, result);
  }

  @Test
  void negativeForward() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "-5.0"));
    fd = new Forward(paramList);
    fd.attach(turtle);
    fd.execute();
    double[] expected = new double[]{0.0, 5.0};
    double[] result = new double[]{turtle.getXCoordinate(), turtle.getYCoordinate()};
    assertArrayEquals(expected, result);
  }

  @Test
  void tiltedForward() {
    turtle = new Turtle(space);
    paramList.add(new Constant("Constant", "10.0"));
    fd = new Forward(paramList);
    fd.attach(turtle);
    fd.execute();
    double[] expected = new double[]{5.0, 8.7};
    DecimalFormat df = new DecimalFormat("#.#");
    double x = Double.parseDouble(df.format(turtle.getXCoordinate()));
    double y = Double.parseDouble(df.format(turtle.getYCoordinate()));
    double[] result = new double[]{x, y};
    assertArrayEquals(expected, result);
  }
}
