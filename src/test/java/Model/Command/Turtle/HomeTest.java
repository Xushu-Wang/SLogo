package Model.Command.Turtle;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Model.Command.Turtle.Home;
import Model.TokenType.Token;
import Model.Turtle;
import Model.TurtleSpace;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HomeTest {

  private Turtle turtle;
  private ArrayList<Token> paramList;
  private Home home;
  private TurtleSpace space;

  @BeforeEach
  void setUp() {
    paramList = new ArrayList<>();
    space = new TurtleSpace("English");
  }

  @Test
  void basicHome() {
    turtle = new Turtle(space);
    home = new Home(paramList);
    home.attach(turtle);
    home.execute();
    double[] expected = new double[]{0.0, 0.0};
    double[] result = new double[]{turtle.getXCoordinate(), turtle.getYCoordinate()};
    assertArrayEquals(expected, result);
  }
}