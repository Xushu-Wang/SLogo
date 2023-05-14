package Model.TokenType;

import Controller.Exception.InvalidCommandException;
import Model.TurtleSpace;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import util.ArgumentMatching;
import util.CommandMatching;
import util.Parser;
import util.PositionMatching;

/**
 * @Purpose: Token Factory Class that generates appropriate Tokens based on user input
 * Used in Interpreter Class in the process of tokenizing String
 * Language is taken as parameter to support different user input
 * TurtleSpace is taken as parameter to check if variable/user-defined functions already exists
 *
 * @author Jay Yoon
 *
 */
public class TokenFactory {

  private final Parser commandType;
  private final Parser position;
  private final Parser numArgument;
  private final TurtleSpace space;
  public static final String COMMAND = "Command";
  public static final String STRING_LABEL = "String";
  public static final String BODY_LABEL = "BodyToken";
  public static final String LIST_OPEN = "[";
  public static final String LIST_CLOSE = "]";
  public static final String VAR_DEFAULT_VALUE = "0.0";


  public TokenFactory(String language, TurtleSpace space) {
    commandType = new CommandMatching(language);
    position = new PositionMatching();
    numArgument = new ArgumentMatching();
    this.space = space;
  }

  /**
   * returns generated Token from user input
   * @param type String: type of Token determined by Parser in Interpreter
   * @param keyword String: specific content of Token to be created
   */
  public Token generate(String type, String keyword)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InvalidCommandException {
    if (type.equals(COMMAND)) {
      if (this.space.containsCommand(keyword)) {
        return this.space.getUserCommand(keyword);
      }
      keyword = commandType.getVariableType(keyword);
      String instructionPath = position.getVariableType(keyword);
      List<Token> parameter = new ArrayList<>();
      Class<?> commandClass = Class.forName(instructionPath);
      Constructor<?> commandConstructor = commandClass.getConstructor(List.class);
      int numParameter = Integer.parseInt(numArgument.getValue(keyword).toString());
      Command thisCommand = (Command) commandConstructor.newInstance(parameter);
      thisCommand.setParamReq(numParameter);
      return thisCommand;
    }
    else {
      String tokenPath = position.getVariableType(type);
      Class<?> clazz = Class.forName(tokenPath);
      Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
      if (type.equals(Token.VARIABLE_TYPE)) {
        if (this.space.containsKey(keyword)) {
          return this.space.getVariable(keyword);
        } else {
          Variable var = (Variable) constructor.newInstance(keyword, VAR_DEFAULT_VALUE);
          this.space.addVariable(var);
          return var;
        }
      }
      return (Token) constructor.newInstance(type, keyword);
    }
  }

  /**
   * generates String Token and adds to given token collection
   * separated from general generate method, as Parser recognizes all alphabetical input as Commands rather than Constants
   * @param s String: current line of user input
   * @param stringTokens Collection<Token>: collection of tokens to be processed in Interpreter
   */
  public void generateStringConstant(String s, Collection<Token> stringTokens) {
    s = s.toLowerCase();
    ArrayList<String> str = new ArrayList<>(Arrays.asList(s.split(Token.SINGLE_SPACE)));
    int index = str.indexOf(Token.USER_COMMAND);
    Constant name = new Constant(STRING_LABEL, str.get(index+1));
    stringTokens.add(name);
  }

  /**
   * generates Body Group Token and adds to given token collection
   *
   * @param start int: starting line of body token
   * @param marker int: starting character body token
   * @param inputList List<String>: string represents user input
   * @param stringTokens Collection<Token>: collection of tokens to be processed in Interpreter
   *
   * returns end index indicating where Interpreter should continue processing input
   */
  public int[] generateGroupToken(int start, int marker, List<String> inputList, Collection<Token> stringTokens)
      throws IndexOutOfBoundsException {
    String content = Token.EMPTY;
    int count = 0;
    boolean complete = false;

    for (int i=start; i<inputList.size(); i++) {
      String[] sub = inputList.get(i).split(Token.WHITESPACE);
      for (int j=0; j<sub.length; j++) {
        if (i==start && j<marker) continue;
        sub[j] = sub[j].trim();
        if (sub[j].equals(LIST_OPEN)) {
          count++;
          if (count==0) complete = true;
        }
        else if (sub[j].equals(LIST_CLOSE)) {
          count--;
          if (count==0) complete = true;
        }
        content += sub[j]+Token.SINGLE_SPACE;
        if (count==0 && complete) {
          content = content.replaceAll("\n\\s\\[", " [");
          content = content.substring(1, content.length()-3);
          stringTokens.add(new BodyToken(BODY_LABEL, content.trim()));
          return new int[]{i, j};
        }
      }
      content += Token.NEW_LINE;
    }
    throw new IndexOutOfBoundsException();
  }


}
