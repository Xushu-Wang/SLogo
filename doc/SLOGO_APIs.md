# SLogo API Design Lab Discussion
### Jay Yoon (jy320), Rodrigo Bassi Guerreiro (rb419), Trevon Helm (tmh85), Andy Wang(xw214)

### TEAM 02


## Planning Questions

* What behaviors (methods) should the Turtle have and what service should it provide?
  * move (forward, backward, etc.)
  * rotation
  * stylistic change (color, design)
  * model (backend) turtle and view (frontend) turtle


* When does parsing need to take place and what does it need to start properly?
  * On button press
  * Starts on proper input (check for valid input)

* What is the result of parsing (not the details of the algorithm) and who receives it?
  * Command Keyword
  * Parameter
  * Controller receives it

* When are errors detected and how are they reported?
  * Before actually parsing, check if input is valid
  * If not, throw error that is caught by frontend view

* What do different commands need to know, when do they know it, and how do they get it?
  * Turtle position (ex. How to handle turtle if out of window)
  * Kept track on Turtle model
  * User input parameters
  * Actual action (which will be concrete subclasses of abstract Command superclass)

* What behaviors does the result of a action need to have to be used by the View?
  * Command modifies model Turtle and view uses the updated version of Turtle to change (ex. position, lines)

* How is the View updated after a action has completed execution?
  * basic update API that checks changes to model Turtle

* What value would Controller(s) have in mediating between the Model and View?
  * taking turtle data (ex. position) and passing as parameters to view


## APIs

### Model/Backend External API: Turtle

* Goals: used to pass position of turtle model onto view

* Contract: returns valid turtle position

* Services: gets current position of turtle (getPosition), sets position of turtle


### View/Frontend External API: CanvasWindow

* Goals: Draw the turtle and the actions of the turtle on the screen.

* Contract: Immutable. It will never change or alter the backend model, it will only read from it and display its contents.

* Services: drawLine, changeCanvasColor, updateTurtle



### Model/Backend Internal API: ParserController

* Goals: To parse the data and return it’s contents in ways that can be used by the Turtle (ideally, a Command model defined by backend)

* Contract: an input must be a valid action defined within the set of possible commands

* Services: parseLine


### View/Frontend Internal API: Line

* Goals: To facilitate the drawing of the line, whether it be straight, curved, or “whatever.”

* Contract: Mutable (in order to make it extendable)

* Services: createLine



## Design

### Backend Design CRCs

This class's purpose or value is to hold turtle information:



| Turtle                                       |              |
|----------------------------------------------|--------------|
| public void moveForward(double distance)     |              |
| public void moveBackward(double distance)    |              |
| public void turnLeft(double angle) 			       |              |
| public void penUp()                          |              |

```java
public class Turtle {
  public void moveForward (double distance);
  public void moveBackward(double distance);
  public void turnRight(double angle);
  public void turnLeft(double angle);
  public void penUp();
  public void penDown();
  public void reset();
}

 ```



### Frontend Design CRCs


This class's purpose or value is to retrieve information about action:

 ConfigController                                              |      |
|---------------------------------------------------------|------|
| public CommandModelInfo getModelInfo(File file)         |      |
| public CommandViewInfo getViewInfo(File file)           | View |
| public boolean isFileValid(File file)                   |      |

This class's purpose or value is to represent a customer's order:
```java
public class ConfigController {
  public CommandModelInfo getModelInfo(File file)
  public CommandViewInfo getViewInfo(File file)
  public boolean isFileValid(File file)
 }
 ```



### Use Cases

* The user types 'fd 50' in the action window, sees the turtle move in the display window leaving a trail, and has the action added to the environment's history.
  * parse action
  * translate action to backend defined functions
  * backend defined functions move turtle
  * turtle display is changed by viewing backend
  * action added to the environment history (own history class)


* The user types '50 fd' in the action window and sees an error message that the action was not formatted correctly.

  * error checking, The controller asks the xml parser to parse the action.
  * we parse 50 fd and see that it doesn’t match any case of the regular expressions.
  * GUI triggers an error and gives out an error message (error window class) specifying the error


* The user types 'pu fd 50 pd fd 50' in the action window and sees the turtle move twice (once without a trail and once with a trail).
  * user types the action 'pu fd 50 pd fd 50' into the action window and presses enter.
  * Parser object parses the action and creates a list of Command objects with the appropriate parameters.
  * PenUpCommand, which sets the pen state to "up".
  * ForwardCommand, which moves the turtle forward 50 units without drawing a trail.
  * PenDownCommand, which sets the pen state to "down".
  * ForwardCommand, which moves the turtle forward another 50 units, this time drawing a trail.
  * Each Command object is executed by calling the execute() method on the Turtle object.
  * Turtle object notifies any registered observers that it has moved, including the CanvasWindow, showing user display with updated position.


* The user changes the color of the environment's background.
    * The user clicks on a palette and chooses the color of the background.
    * The controller notices a change in the state of the background.
    * The GUI class switches the background color into the color that the user chooses. 
