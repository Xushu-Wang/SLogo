package Model.Command.Turtle;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;
/**
 * @Purpose: Show Command that allows user to show turtle and enable it to be shown on window
 * @author Jay Yoon
 *
 */
public class ShowTurtle extends Command {

  public ShowTurtle(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Show command
   * no parameters are needed
   */
  @Override
  public Double execute() {
    this.getTurtle().show();
    return 1.0;
  }

}
