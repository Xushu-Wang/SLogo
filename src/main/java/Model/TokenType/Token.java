package Model.TokenType;

/**
 * @Purpose: Token parent class that represents a Token in SLogo. Used to tokenize input strings from user to allow for
 * Interpreter to process and recognize user input
 *
 * @author Jay Yoon
 *
 */
public class Token {

  public static final String WHITESPACE = "\\s+";
  public static final String SINGLE_SPACE= " ";
  public static final String NEW_LINE = "\n";
  public static final String EMPTY = "";
  public static final String PERIOD = "\\.";
  public static final Character HASH = '#';
  public static final Character LINE = '\n';
  public static final String VARIABLE_TYPE = "Variable";
  public static final String USER_COMMAND = "to";
  private String type;
  private String content;
  private boolean paramStatus;

  public Token(String type, String content, boolean status) {
    this.type = type;
    this.content = content;
    this.paramStatus = status;
  }

  /**
   * returns boolean that represents whether token can be a parameter to function
   */
  public boolean isParamStatus() {
    return this.paramStatus;
  }

  /**
   * returns Double that represents value of Token
   */
  public Double getValue() {
    if (this.content==null) return null;
    return Double.parseDouble(this.content);
  }

  /**
   * returns String that represents type of Token
   */
  public String getType() {
    return this.type;
  }

  /**
   * updates value of Token
   * @param value Double: updated value of Token
   */
  public void setValue(Double value) {
    content = String.valueOf(value);
  }

  /**
   * returns String that represents content of Token
   */
  public String getContent() {
    return this.content;
  }

  /**
   * toString method to return Token type as String
   */
  @Override
  public String toString() {
    return this.type;
  }
}
