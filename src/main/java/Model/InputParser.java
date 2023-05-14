package Model;

import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Purpose: Input Parser class that processes raw String input from user
 * Processes minor details and minimize any assumptions of input strings
 * @author Jay Yoon
 *
 */
public class InputParser {

  /**
   * process raw input to ignore comments, multiple empty spaces, and uppercase distinctions
   * returns List of Strings to be executed in Interpreter
   */
  public List<String> processInput(String rawInput) {
    rawInput = rawInput.toLowerCase().trim();
    rawInput = rawInput.replaceAll("\n\\[", " [");
    return sliceComments(rawInput);
  }

  public List<String> processLine(String s) {
    ArrayList<String> substrings = new ArrayList<>(List.of(s.split(Token.WHITESPACE)));
    this.processEmpty(substrings);
    return substrings;
  }

  private List<String> sliceComments(String rawInput) {
    int index = 0;
    while(index!=-1) {
      index = rawInput.indexOf(Token.HASH);
      if (index==-1) break;
      int endIndex = rawInput.indexOf(Token.LINE, index);
      String comment = rawInput.substring(index, endIndex);
      rawInput = rawInput.replace(comment, Token.EMPTY);
    }
    List<String> inputList = new LinkedList<>(Arrays.asList(rawInput.split(Token.NEW_LINE)));
    this.processEmpty(inputList);
    return inputList;
  }

  private void processEmpty(List<String> inputList) {
    for(int i=0; i<inputList.size(); i++) {
      inputList.set(i, inputList.get(i).trim());
      if (inputList.get(i).equals(Token.EMPTY)) {
        inputList.remove(i);
        i--;
      }
    }
  }
}
