package Model.Command.Control;
import Model.Interpreter;
import Model.TokenType.BodyToken;
import Model.TokenType.Command;
import Model.TokenType.Token;
import Model.TokenType.TokenFactory;
import Model.TokenType.Variable;
import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Repeat extends Command {
  public static final String COUNTER_NAME = ":repcount";
  private TokenFactory internalTF;
  private Interpreter internalParser;

  public Repeat(List<Token> paramList) {
    super(paramList);
  }

  @Override
  public Double execute()
      throws InvalidCommandException, MissingArgumentException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidArgumentException {
    internalTF = new TokenFactory(this.getTurtle().getSpace().getLanguage(), this.getTurtle().getSpace());
    internalParser = new Interpreter(this.getTurtle().getSpace());

    Variable count = (Variable) internalTF.generate(VARIABLE_TYPE, COUNTER_NAME);
    int repeatTimes = (int) Math.round(this.getParameter().get(0).getValue());
    Double result = null;
    for (int i=0; i<repeatTimes; i++) {
      result = internalParser.processInput(this.getParameter().get(1).getContent(), this.getTurtle());
      count.setValue((double)i);
    }
    return result;
  }
}