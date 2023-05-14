package Model.Command.UserDefined;

import Model.Interpreter;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import Model.TokenType.Variable;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Purpose: Custom User Command that holds information about command name, parameter requirements, and actual content to be executed
 * This enables users to call user commands later and allows Interpreter to recognize custom commands
 * This is associated with the MakeUserInstruction Command, that sets up this UserCommand object
 * @author Jay Yoon
 *
 */
public class UserCommand extends Command {
  private String name;
  private ArrayList<Variable> variables;
  private String content;
  private Interpreter internalParser;
  private TokenFactory internalTF;

  public UserCommand(List<Token> paramList) {
    super(paramList);
    this.variables = new ArrayList<>();
  }

  /**
   * execution of this command
   * if this command requires parameters, sets input values as variable values, updating if necessary
   * then executes its command content using internal interpreter
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    if (this.getParamReq()>0) {
      for (int i=0; i<this.getParamReq(); i++) {
        variables.get(i).setValue(this.getParameter().get(i).getValue());
      }
    }
    return internalParser.processInput(content, this.getTurtle());
  }

  /**
   * sets configuration of this command, including function name, parameter variable lists, and content
   * if this command requires parameters, generates variables if not already present in workspace
   * the size of this variable list is used to set the required parameter number, thus separated from constructor
   *
   * @param name String: name of user defined function
   * @param varNames List<String>: names of required parameters
   * @param content String: content of function to be executed when called
   */
  public void setConfiguration(String name, List<String> varNames, String content)
      throws InvalidCommandException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    internalParser = new Interpreter(this.getTurtle().getSpace());
    internalTF = new TokenFactory(this.getTurtle().getSpace().getLanguage(), this.getTurtle().getSpace());

    this.name = name;
    for (int i=0; i<this.getParamReq(); i++) {
      variables.add((Variable) internalTF.generate(VARIABLE_TYPE, varNames.get(i)));
    }
    this.content = content;
  }

  /**
   * returns name of function as String value
   */
  public String getName() {
    return this.name;
  }
}
