package util;

public class ArgumentMatching extends Parser {

  private static final String numArgument = "Argument";

  /**
   * Parser for the number of arguments correspondent to each command.
   */

  public ArgumentMatching() {
    super();
    setPatterns(numArgument);
  }

}
