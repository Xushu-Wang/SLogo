package util;

public class PositionMatching extends Parser {

  private static final String position = "Position";

  /**
   * Parser for finding the locations of different command classes
   */

  public PositionMatching() {
    super();
    setPatterns(position);
  }
}
