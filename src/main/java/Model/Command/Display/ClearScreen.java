package Model.Command.Display;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

public class ClearScreen extends Command {

  /**
   * Clear Screen command, clear the trails on the screen and reset the turtle position
   * @param paramList
   */

  public ClearScreen(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute() {

    double change = this.getTurtle().calcDistanceMoved(0, 0);
    this.getTurtle().setLocation(0, 0);

    this.getTurtle().getSpace().getBackground().clearScreen();

    return change;
  }

}
