package Model.Command.Display;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SetBackground extends Command {

  /**
   * SetBackground command, set the color of the background through giving hexadecimal color value;
   * @param paramList
   */

  protected SetBackground(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    double color = getParameter().get(0).getValue();

    getTurtle().getSpace().getBackground().setColor(String.valueOf(color));

    return color;
  }
}
