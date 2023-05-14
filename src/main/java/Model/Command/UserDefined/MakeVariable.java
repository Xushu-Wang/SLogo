package Model.Command.UserDefined;

import Model.TokenType.Command;
import Model.TokenType.Token;
import java.util.List;

/**
 * @Purpose: MakeVariable Command enables user to define their own variable with specified value
 * This enables users to save the user defined variables in workspace window and treat them as constant values
 * @author Jay Yoon
 *
 */
public class MakeVariable extends Command {
  public MakeVariable(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual MakeVariable command
   * @param0 Variable Token holding String content that represents variable name
   * @param1 Constant Token holding Double value that represents variable value
   */
  @Override
  public Double execute() {
    Double value = this.getParameter().get(1).getValue();
    this.getParameter().get(0).setValue(value);
    return value;
  }
}
