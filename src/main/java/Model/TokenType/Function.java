package Model.TokenType;

/**
 * @Purpose: Function Token that holds function keywords as value
 * @author Jay Yoon
 *
 */
public class Function extends Token {

  public Function(String type, String keyword) {
    super(type, keyword, false);
  }

}
