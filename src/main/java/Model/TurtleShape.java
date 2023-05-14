package Model;

public class TurtleShape {
  public interface tShape{
    double getCode();

  }

  public enum turtleShape implements tShape{

    Default(0),
    Rectangle(1),
    Triangle(2),
    Square(3);

    private double code;

    turtleShape(double code){
      this.code = code;
    }

    public double getCode(){
      return code;
    }
  }
}
