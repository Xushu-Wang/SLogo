package Model.TokenType;

/**
 * @Purpose: Body Token that holds String content inside brackets
 * Used in Loops, Control Structures, Custom User Instructions that require square brackets
 * @author Jay Yoon
 *
 */
public class BodyToken extends Token {

  public BodyToken(String type, String content) {
    super(type, content, true);
  }
}
