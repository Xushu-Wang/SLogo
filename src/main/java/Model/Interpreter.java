package Model;

import Model.Command.UserDefined.MakeUserInstruction;
import Model.TokenType.Command;
import Model.TokenType.Constant;
import Model.TokenType.ListOpen;
import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import util.RegexMatching;

/**
 * @Purpose: Interpreter class that processes input from user and executes commands
 *
 * @author Jay Yoon
 *
 */
public class Interpreter {

  public static final String CONSTANT = "Constant";
  private final TokenFactory tf;
  private final TurtleSpace space;
  private ArrayDeque<Token> tokens;
  private Double lastResult;
  private final Parser wordType;
  private final InputParser processor;

  public Interpreter(TurtleSpace space) {
    this.space = space;
    tf = new TokenFactory(space.getLanguage(), space);
    tokens = new ArrayDeque<>();
    wordType = new RegexMatching();
    processor = new InputParser();
  }

  /**
   * returns Double value that represents the result value of the last executed command
   *
   * @param rawInput String: raw input String from user given through text area
   * @param turtle Turtle: turtle that is attached to this workspace
   */
  public Double processInput(String rawInput, Turtle turtle)
      throws InvalidCommandException, MissingArgumentException, InvalidArgumentException, IllegalAccessException {
    List<String> inputList = processor.processInput(rawInput);

    for (int i = 0; i < inputList.size(); i++) {
      ArrayDeque<Token> stringTokens = new ArrayDeque<>();
      List<String> substrings;

      for (int j = 0; j < this.substrings(inputList, i); j++) {
        substrings = processor.processLine(inputList.get(i));
        try {
          String type = wordType.getVariableType(substrings.get(j));
          Token generated = tf.generate(type, substrings.get(j));

          if (generated.getClass().equals(ListOpen.class)) {
            int[] endIndex = tf.generateGroupToken(i, j, inputList, stringTokens);
            i = endIndex[0];
            j = endIndex[1];
          }
          else if (generated.getClass().equals(MakeUserInstruction.class)) {
            stringTokens.add(generated);
            tf.generateStringConstant(inputList.get(i), stringTokens);
            j++;
          }
          else stringTokens.add(generated);

        } catch (InvalidCommandException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
          throw new InvalidCommandException(e.getMessage());
        }
      }
      this.tokens = stringTokens;
      lastResult = this.execute(turtle);
    }
    return lastResult;
  }

  private Double execute(Turtle turtle) throws IllegalAccessException {
    while (!tokens.isEmpty()) {
      if (tokens.element().isParamStatus()) {
        tokens.add(tokens.poll());
        continue;
      }

      Command token = (Command) tokens.poll();
      List<Token> arguments = new ArrayList<>();
      token.attach(turtle);
      boolean executable = this.checkArguments(token, arguments);
      if (executable) {
        Double result = this.runCommand(token, arguments, turtle);
        if (this.complete(tokens)) return result;
      }
    }
    return 0.0;
  }

  private Double runCommand(Command token, List<Token> arguments, Turtle turtle) throws IllegalAccessException {
    token.setParameter(arguments);
    Double result = null;
    try {
      turtle.addHistory(token);
      result = token.execute();
      tokens.add(new Constant(CONSTANT, String.valueOf(result)));
    } catch (NullPointerException e) {
      this.rollback(token, arguments, null);
    } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
             InstantiationException | IllegalAccessException | InvalidCommandException |
             InvalidArgumentException | MissingArgumentException e) {
      throw new IllegalAccessException();
    }
    return result;
  }

  private void rollback(Command token, List<Token> arguments, Token args) {
    tokens.add(token);
    tokens.addAll(arguments);
    if (args != null) {
      tokens.addFirst(args);
    }
  }

  private boolean checkArguments(Command token, List<Token> arguments)
      throws IllegalAccessException {
    for (int i = 0; i < token.getParamReq(); i++) {
      Token args = tokens.poll();
      if (args == null) {
        throw new IllegalAccessException();
      }
      if (args.isParamStatus()) {
        arguments.add(args);
      }
      else {
        this.rollback(token, arguments, args);
        return false;
      }
    }
    return true;
  }

  private boolean complete(ArrayDeque<Token> initialTokens) {
    for (Token token : initialTokens) {
      if (token.getType().equals(TokenFactory.COMMAND)) {
        return false;
      }
    }
    return true;
  }

  private int substrings(List<String> inputList, int i) {
    List<String> substrings = processor.processLine(inputList.get(i));
    return substrings.size();
  }


}

