package Model.TokenType;

/**
 * @Purpose: Comment Token that holds String content of comment to be ignored
 * @author Jay Yoon
 *
 */
public class Comment extends Token {

  public Comment(String type, String content) {
    super(type, content, false);
  }
}
