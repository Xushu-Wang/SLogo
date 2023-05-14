package Model.TokenType;

/**
 * @Purpose: ListOpen Token that marks the start of Body Token
 * Used in generation of Body Tokens
 * @author Jay Yoon
 *
 */
public class ListOpen extends Token {

  public ListOpen(String type, String keyword) {
    super(type, null,false);
  }
}
