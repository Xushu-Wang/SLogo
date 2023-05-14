package util;

public class CommandMatching extends Parser {

  private static final String DEFAULT_LANGUAGE = "English";

  /**
   * Parser for types of Command, e.g. Forward, And, Sum, etc
   * @param language
   */

  public CommandMatching(String language) {
    super();
    setPatterns(language);
  }

  public CommandMatching() {
    super();
    setPatterns(DEFAULT_LANGUAGE);
  }
}
