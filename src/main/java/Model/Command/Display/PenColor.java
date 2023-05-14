package Model.Command.Display;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PenColor extends Command {

  /**
   * Pencolor command, return the current pen color
   * @param paramList
   */

  protected PenColor(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    String color = getTurtle().getPen().getColor();

    return Double.valueOf(color);
  }
}
