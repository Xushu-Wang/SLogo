package Model.Command.Display;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SetPenSize extends Command {

  /**
   * SetPenSize command, set the size of the pen through giving a double
   * @param paramList
   */
  protected SetPenSize(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {

    double size = getParameter().get(0).getValue();
    getTurtle().getPen().setWidth(size);

    return size;
  }
}
