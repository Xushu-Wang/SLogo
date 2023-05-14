package Model.TokenType;

/**
 * @Purpose: Variable Token that holds numeric value to be treated as constant values
 * Can be used as parameter for command execution
 * Saved and can be updated in same window workspace
 * @author Jay Yoon
 *
 */
public class Variable extends Token {

  public Variable(String type, String value) {
    super(type, value, true);
  }

  @Override
  public String toString() {
    return super.toString()+Token.SINGLE_SPACE+super.getValue();
  }

}
