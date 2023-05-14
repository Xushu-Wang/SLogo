package Model.Command.Display;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SetPenColor extends Command {

  /**
   * Set pencolor command, set the color of pen through giving hex color value
   * @param paramList
   */

  protected SetPenColor(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {

    double color = getParameter().get(0).getValue();
    getTurtle().getPen().setColor(String.valueOf(color));

    return color;
  }
}
