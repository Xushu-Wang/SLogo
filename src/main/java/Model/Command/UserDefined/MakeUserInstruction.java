package Model.Command.UserDefined;

import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.Turtle;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Purpose: MakeUserInstruction Command enables user to define their own set of instruction with custom name
 * This enables users to save the user defined command in workspace window and call them later to be executed
 * @author Jay Yoon
 *
 */
public class MakeUserInstruction extends Command {

  public MakeUserInstruction(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual MakeUserInstruction command
   * @param0 Constant Token holding String content value that represents name of user-defined function
   * @param1 Body Token that represents any parameter variables the function needs for execution
   * @param2 Body Token that holds the actual list of command process to be executed
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {

    String name = this.getParameter().get(0).getContent();
    String[] split = this.getParameter().get(1).getContent().trim().split(WHITESPACE);
    List<String> varNames = new ArrayList<>();
    for (String s : split) {
      if (!s.equals(EMPTY)) varNames.add(s);
    }
    String content = this.getParameter().get(2).getContent();

    UserCommand userCommand = new UserCommand(new ArrayList<>());
    userCommand.setParamReq(varNames.size());
    userCommand.attach(this.getTurtle());
    userCommand.setConfiguration(name, varNames, content);
    this.getTurtle().getSpace().addCommand(userCommand);

    return 1.0;
  }
}
