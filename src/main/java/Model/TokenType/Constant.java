package Model.TokenType;

/**
 * @Purpose: Constant Token that holds String values or Double values, both stored as String
 * Can be used as parameter for command execution
 * @author Jay Yoon
 *
 */
public class Constant extends Token {

  public Constant(String type, String value) {
    super(type, value, true);
  }
}
