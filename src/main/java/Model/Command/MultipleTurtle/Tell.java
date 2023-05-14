package Model.Command.MultipleTurtle;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import util.RegexMatching;
/**
 * @Purpose: Tell Command that allows user to activate specified turtles for all incoming commands hereafter
 * The command creates turtles if not already present
 * @author Jay Yoon
 *
 */
public class Tell extends Command {
  private final Parser wordType;
  private TokenFactory internalTF;

  public Tell(List<Token> paramList) {
    super(paramList);
    wordType = new RegexMatching();
  }

  /**
   * execution of actual Tell command
   *
   * @param0 Body Token that contains information of turtle IDs to be activated
   */
  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalTF = new TokenFactory(this.getTurtle().getSpace().getLanguage(), this.getTurtle().getSpace());
    List<Integer> target = new ArrayList<>();
    String[] ids = this.getParameter().get(0).getContent().trim().split(SINGLE_SPACE);
    for (String id : ids) {
      String type = wordType.getVariableType(id);
      Token generated = internalTF.generate(type, id);
      target.add((int) Double.parseDouble(generated.getContent()));
    }

    //addifabsent done twice
    for (int id : target) this.getTurtle().addTurtleIfAbsent(id);
    this.getTurtle().activateID(target);

    return (double)target.get(target.size()-1);
  }
}
