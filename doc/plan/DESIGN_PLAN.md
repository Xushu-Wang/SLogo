# SLogo Design Plan
### Jay Yoon, Rodrigo Bassi Guerreiro, Trevon Helm, Andy Wang
### TEAM 02


## Introduction
Our team seeks to create an easy-to-use application for creating and manipulating graphics using simple Logo language. The interpreter allows the user to move the turtle around the screen, draw lines and shapes, and create complex designs and patterns with just a few simple commands. Our project is designed to be intuitive and user-friendly, with a graphical user interface that makes it easy to enter commands and see the results update in real time.


## Configuration File Format
```xml
	<names>
		<main>forward</main>
		<alternate>fd</alternate>
		<alternate>avante</alternate>
		…
	</names>
	<implementation>
		<numparams>1</numparams>
		<classname>ForwardCommand</classname>
	</implementation>
	<documentation>
	<description>Moves turtle forward in current direction by specified value</description>
	<example>forward 50</example>
		<params>movement length</params>
		<returns>new x and y positions</returns>
		<category>motion</category>
	</documentation>
```


## User Interface

All UI Wireframes are found at **/doc/plan/wireframe**
* Our UI is designed to be easy to use, with primary emphasis on showing the canvas and the turtles creations, though with related information and help easy to access through options on the left.
* On the top right we have buttons that will
  * Dropdown box that will allow you to select a language.
  * Save button that will save the current saved variables and functions
  * Load button that will load a .slogo file
  * About button that will detail authors.
* On the bottom left is a window that will show either saved variables or saved functions (depending on what is selected on the top of the window).
* On the left side we have an informational box that will have help documentation, action history, and customization options depending on the tab chosen. The image shows a draft of what should appear by selecting history.
* Customization options that can change when selecting the customization tab.
* If an invalid is created, the program will display an error message on the top of the canvas, and return back to its previous state.
  * All errors will follow the same format, even for an invalid file.




## Design Overview
<img width="826" alt="스크린샷 2023-02-20 오후 9 54 54" src="https://user-images.githubusercontent.com/90667275/220236714-809eeb92-db06-400c-9d61-e80e449f6d1e.png">

**Turtle** class represents the graphical object that moves around the screen and draws lines and shapes. It has methods for moving forward and backward, turning left and right, raising and lowering the pen, and changing the pen color, size, and other attributes.

**History** class represents the action history of the Logo interpreter, keeping track of all the commands that have been executed. It has methods for adding a action to the history, getting the last action, checking if there is a action to undo, and undoing the last action.

**Canvas** class represents the window that the Turtle will be drawing in. Its primary jobs will be to draw the lines that following the turtle and to update the Turtle’s position graphically as told from the model,

**Command** class is an abstraction of all kinds of commands and operations. It includes an operation interface, implemented by boolean operations class and math operations class. Each subcommand implements the action class, using the API from operations to realize certain functions available in turtle.

**Operations** Interface is composed of several static classes/APIs usable for other classes. It aims at realizing basic operations including boolean, math, loops, branching statements, etc.

**Turtle query** class is a special class in the realm of action, but being realized and implemented in a different way. It has access to the turtle class and returns specific values and attributes of the turtle.

**FileParser** class is responsible for taking a file as input, checking if it is valid, extracting information from it, and making different records for the model and the view based on their different needs. It should also take in .slogo files or a String and process those into a list of commands that can be used by the model.

**Controller** class mediates the interactions between the model and the view, being mainly responsible for responding to user input by updating the view and the model appropriately

**SideView** class displays and manages elements of the UI that Canvas does not display, such as reference windows and updating languages.

## Design Details
| Turtle                                                  |              |
|---------------------------------------------------------|--------------|
| public void moveForward(double distance)                |              |
| public void moveBackward(double distance)               |              |
| public void turnLeft(double angle) 			    |              |
| public void penUp()                                     |              |

Turtle class is responsible for moving around the screen and drawing lines and shapes. It communicates with the Canvas class to update the graphical representation of the turtle's movements. This class is a good choice because it encapsulates the behavior of the turtle and makes it easy to change its attributes in a centralized way.



| History                                                 |              |
|---------------------------------------------------------|--------------|
| public void add(Command action)                        | Command      |
| public Command getLatest()                              |              |
| public void undoLatest()                                |              |

History class keeps track of all the commands that have been executed and is used for undoing previous commands. It is updated as new Command object is created from user textbox input. This class is a good choice because it makes it easy to implement an undo feature, which is a common requirement for interactive graphics applications. An alternative could be to use a stack or queue data structure to store the action history, but this would require exposing data structure.

Command class is responsible for executing different kinds of commands, such as movement and pen manipulation, and also includes an Operations Interface that is used to implement math and boolean operations. The Turtle Query class is a special class within the Command class that has access to the Turtle class and returns specific values and attributes of the turtle.

 FileParser                                               |              |
|---------------------------------------------------------|--------------|
| public CommandModelInfo getModelInfo(File file)         |              |
| public CommandViewInfo getViewInfo(File file)           |              |
| public boolean isFileValid(File file)                   |              |
| public List<String> getCommandStringList(String commands)|              |
| public List<String> getCommandStringList(File file)      |              |


FileParser: this class would be directly linked to the controller, but would keep all the file processing inside one place, returning the necessary information (separated by what needs to be used for the model and the view) in correspondingly named records. It would also check the validity of information, throwing errors if needed.


| Canvas                                  |     |
|----------------------------------------|-----|
| public void update(Turtle tordle) 	     |     |
| public void clear()                     |     |
| public boolean speedup()                |     |
| public boolean slowdown()               |     |
| public boolean step()                   |     |  

Canvas: This class will be in charge of showing the movement of the turtle. It will keep track of the animations, managing the lines, and showing the movement of the turtle. This class will have to be in the know of any changes in the turtle, and it will also have to create and manage any new Line classes. The signatures are purposely sparse, as many of the specific design details should be contained with the Canvas itself– though to update the Canvas, we will need to update the turtle. (Though more abstraction may be found when implementing.)

| SideView                                          |     |
|--------------------------------------------------|-----|
| public boolean updateBackgroundColors()           |     |
| public boolean updateWindowColors()               |     |
| public boolean changeLanguage(String language)    |     |
| public void displayAbout()                        |     |
| public void showErrorMessage(event e)		   |     |

SideView: Class that is in charge of the entire UI, it will get information from the controller to properly display information, whether that be by passing it to the Canvas, reacting to a button press to switch a window, or just updating the language. Many methods are boolean to help with testing; a false result could be indicative of other problems that an exception may illuminate.


## Design Considerations
1. Using Observer Pattern (Turtle-CanvasWindow) (History-Turtle)

Using the Observer pattern to decouple the different parts of the system and allow them to communicate through notifications can be a powerful design choice. In this approach, the Turtle object could be the "subject" that sends notifications to the GUI when the turtle moves or when a line is drawn, while the GUI could be the "observer" that receives these notifications and updates the display accordingly. This would also allow the Turtle object to register itself as an observer to receive notifications when a new action is added to History. Using this approach can help to decouple the different parts of the system and make it easier to add new features in the future.

2. Having Pen class

Creating a separate Pen class to handle the drawing of lines and shapes on the screen can provide a more modular and flexible approach. The Pen class would be responsible for translating the Turtle's movements and commands into actual lines and shapes on the screen. This would allow the Turtle to focus solely on its own movements and state, while delegating the drawing responsibilities to the Pen. However, this approach could add some complexity to the program and require careful coordination between the Turtle and Pen classes.

3. Separation of Tasks between Model Turtle and View Turtle

Separating the tasks between the Model Turtle and View Turtle can provide a more clear separation of concerns and responsibilities. The Model Turtle would be responsible for maintaining its own state and executing the commands, while the View Turtle would be responsible for rendering the turtle and its movements on the screen. This would allow the View Turtle to focus solely on the visualization of the turtle, while the Model Turtle handles the underlying logic. However, this approach could add some complexity to the program and require careful coordination between the two turtles to ensure that the visualization accurately reflects the turtle's movements and state.


## Test Plan
JUnit Test for smallest testable units

>Unit 1: Operation interface and its subclasses.

Test will include most common boolean/math operations to ensure that the math class is throwing errors as expected, i.e. 0 divisor, infinity, etc

Happy: The class works exactly as the intentions, it can be used as a basic calculator which results in correct values.

Sad: Encounter unreasonable scenarios which isn’t consistent with math conventions/boolean conventions


>Unit 2: Canvas (The sketch of GUI)

Test will include basic instructions the users can give to the GUI, including change color both for background and (turtle if possible), look for history of commands, and give commands to turtle (It is ok turtle might not be working though).

Happy: The user has absolute control of user interface without any bugs generating, it also provide proper error messages for multiple misdemeanors.

>Unit 3: Turtle Model (Basic movement of turtle)

Test will include basic instructions users can give to the turtle, including forward, backward, rotation, functions that are easy to be implemented.

Happy: The GUI should be able to display the correct position and angle of direction of the turtle based on the legitimate user commands. Also, the trace of turtle should also be displayed on the canvas. If the action is not in the text action pool, or illegitimate, the GUI will generate corresponding error messages to help users modify the input.


## Team Responsibilities

 * Jay Yoon: responsible for Models (backend)
   * will mainly work on implementing Observer pattern for Turtle and History
   * communication between controller (parser and commandController)

 * Andy Wang: responsible for Models (backend)
   * will mainly work on implementing Observer pattern and Types of Commands
   * subclasses of action structure (boolean, math, etc.)

 * Trevon Helm: responsible for View (frontend)
   * will mainly work on displaying line and turtle on canvas window
   * with side window showing history and user parameters

 * Rodrigo Bassi Guerreiro: responsible for Controller and View
   * will mainly work on checking user input error, passing Command object to backend
   * helping frontend with canvas window and side window
