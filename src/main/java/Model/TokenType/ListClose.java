package Model.TokenType;

/**
 * @Purpose: ListClose Token that marks the end of Body Token
 * Used in generation of Body Tokens
 * @author Jay Yoon
 *
 */
public class ListClose extends Token {

  public ListClose(String type, String keyword) {
    super(type, null, false);
  }
}
