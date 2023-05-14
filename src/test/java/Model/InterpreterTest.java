package Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InterpreterTest {

  Interpreter parser;
  Turtle turtle;
  private TurtleSpace space;

  @BeforeEach
  void setup() {
    space = new TurtleSpace("English");

    parser = new Interpreter(space);
    turtle = new Turtle(space);
  }

  @Test
  void basicParsing()
      throws InvalidCommandException, MissingArgumentException, InvalidArgumentException {
    String input = "fd 50";
    parser.processInput(input, turtle);
    double updatedXValue = turtle.getXCoordinate();
    double expected = 50.0;
    assertEquals(expected, updatedXValue);
  }

  @Test
  void testParsingExceptions() {
    String invalid = "invalid";
    assertThrows(InvalidCommandException.class, () -> {
      parser.processInput(invalid, turtle);
    });

    String missing = "forward";
    assertThrows(MissingArgumentException.class, () -> {
      parser.processInput(missing, turtle);
    });
  }
}