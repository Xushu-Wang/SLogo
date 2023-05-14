package Model.Command.MultipleTurtle;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.Interpreter;
import Model.TokenType.Command;
import Model.TokenType.Token;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * @Purpose: Ask Command that allows user to activate specified turtles
 * The command creates turtles if not already present
 * @author Jay Yoon
 *
 */
public class Ask extends Command {
  private Interpreter internalParser;

  public Ask(List<Token> paramList) {
    super(paramList);
  }

  /**
   * execution of actual Ask command
   *
   * @param0 Body Token that contains information of turtle IDs to be activated
   * @param1 Body Token that contains commands to be executed for specified turtles
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalParser = new Interpreter(this.getTurtle().getSpace());

    List<Integer> target = new ArrayList<>();
    String[] ids = this.getParameter().get(0).getContent().trim().split(SINGLE_SPACE);
    for (String id : ids) target.add(Integer.parseInt(id));

    for (int id : target) this.getTurtle().addTurtleIfAbsent(id);
    this.getTurtle().activateID(target);
    double result = internalParser.processInput(this.getParameter().get(1).getContent(), this.getTurtle());
    this.getTurtle().deactivateID(target);

    return result;
  }
}
