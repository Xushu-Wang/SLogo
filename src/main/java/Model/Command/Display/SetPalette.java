package Model.Command.Display;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.awt.Color;

public class SetPalette extends Command {

  /**
   * Set palette command, set the color of background through giving R, G ,B value;
   * @param paramList
   */

  protected SetPalette(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    double r = getParameter().get(0).getValue();
    double g = getParameter().get(1).getValue();
    double b = getParameter().get(2).getValue();

    Color c = new Color((float) r, (float) g,  (float) b);

    getTurtle().getSpace().getBackground().setColor(c.toString());

    return Double.valueOf(c.toString());

  }
}
