package Model;

import Controller.Exception.InvalidArgumentException;
import Controller.Exception.InvalidCommandException;
import Controller.Exception.MissingArgumentException;

public class Model {

  public static void main(String[] args)
      throws InvalidCommandException, MissingArgumentException, InvalidArgumentException, IllegalAccessException {
    TurtleSpace space = new TurtleSpace("English");
    Turtle t = new Turtle(space);

    String command = "to square [ :distance ]\n"
        + "[\n"
        + "  repeat 4 [\n"
        + "    fd :distance\n"
        + "    rt 90\n"
        + "  ]\n"
        + "]\n"
        + "\n"
        + "square 5";

//    String command = "dotimes [ :t 360 ] [\n"
//        + "fd 1\n"
//        + "rt / sin :t 2\n"
//        + "]";

//    String command = "to regularShape [ :distance :angle ]\n"
//        + "[\n"
//        + "  repeat quotient 360 :angle\n"
//        + "  [\n"
//        + "    fd :distance\n"
//        + "    rt :angle\n"
//        + "  ]\n"
//        + "]\n"
//        + "\n"
//        + "cs\n"
//        + "regularShape 100 30\n"
//        + "regularShape 100 40\n"
//        + "regularShape 100 45\n"
//        + "regularShape 100 60\n"
//        + "regularShape 100 90\n"
//        + "regularShape 100 120";
//    String command = "fd sum sum sum sum 10 20 30 5 5";
    //String command = "fd 50";
//    String command = "# same as fd 100\n"
//        + "fd fd 50\n"
//        + "\n"
//        + "# same as rt 100 fd 100\n"
//        + "fd rt 100\n"
//        + "\n"
//        + "# same as fd 100\n"
//        + "fd * greater? 5 3 100";
    // * 1 100 fd
//    String command = "pd";
//    String command = "make :random sum 1 random 100\n"
//        + "fd :random";
//     String command = "fd sum 1 random 100";
//     String command = "# move to side of square\n"
//        + "pu\n"
//        + "fd 50\n"
//        + "rt 90\n"
//        + "\n"
//        + "# draw square\n"
//        + "pd\n"
//        + "fd 50\n"
//        + "rt 90\n"
//        + "fd 100\n"
//        + "rt 90\n"
//        + "fd 100\n"
//        + "rt 90\n"
//        + "fd 100\n"
//        + "rt 90\n"
//        + "fd 50\n"
//        + "\n"
//        + "# move back to center\n"
//        + "pu\n"
//        + "home";

    Interpreter myParser = new Interpreter(space);

    myParser.processInput(command, t);
  }
}
