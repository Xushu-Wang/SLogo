package Model.Command.Control;

import static org.junit.jupiter.api.Assertions.*;

import Controller.Exception.InvalidArgumentException;
import Model.Command.Turtle.Forward;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.Turtle;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TurtleSpace;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class IfTest {
  private Command iftest;
  private Turtle t;
  private TurtleSpace space;

  @Test
  void basic()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    space = new TurtleSpace("English");

    List<Token> expr = new ArrayList<>();
    expr.add(new Token("Constant", "1" , true));
    List<Command> command = new ArrayList();
    t = new Turtle(space);
    List<Token> parameter = new ArrayList<>();
    parameter.add(new Token("Constant", "50" , true));

    Command fd = new Forward(parameter);
    fd.attach(t);

    command.add(fd);

    iftest = new If(expr);
    iftest.execute();

    assertEquals(50.0, t.getXCoordinate());
  }
}