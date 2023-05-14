package util;

import Controller.Exception.InvalidCommandException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public abstract class Parser {

  private static final String LANGUAGE_RESOURCE_PATH = "languages.";
  private final List<Entry<String, Pattern>> myTokens;

  /**
   * Parser: return a list of entrys (key, pattern pairs) for future mapping
   */

  protected Parser() {
    myTokens = new ArrayList<>();
  }

  protected void setPatterns(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
    for (String key : Collections.list(resources.getKeys())) {
      myTokens.add(new SimpleEntry<>(key,
          Pattern.compile(resources.getString(key), Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Get the key from value
   * @param text
   * @return entry key
   * @throws InvalidCommandException
   */

  public String getVariableType(String text) throws InvalidCommandException {
    for (Entry<String, Pattern> e : myTokens) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    throw new InvalidCommandException(String.format("Invalid command given: %s", text));
  }

  /**
   * Get the value from the key, mostly used for argument number
   * @param text
   * @return entry value
   * @throws InvalidCommandException
   */

  public Pattern getValue(String text) throws InvalidCommandException {
    for (Entry<String, Pattern> e : myTokens) {
      if (text.equals(e.getKey())) {
        return e.getValue();
      }
    }

    throw new InvalidCommandException(String.format("Invalid command given: %s", text));
  }


  // Check if the text match the pattern
  private boolean match(String text, Pattern regex) {
    return text != null && regex.matcher(text.trim()).matches();
  }
}
