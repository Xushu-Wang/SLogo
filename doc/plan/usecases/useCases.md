>The user types 'fd 50' in the action window, and sees the turtle move in the display window leaving a trail, and the action is added to the environment's history.
* The user types the action 'fd 50' into the action window and presses enter.
* The Parser object parses the action and creates a new Command object with the appropriate parameters.
* The Command object is executed by calling the execute() method on the Turtle object, which moves the turtle forward 50 units.
* The Turtle object notifies any registered observers that it has moved, including the History object, which adds the action to its history.
* The CanvasWindow updates the display to show the turtle's new position and the line it has drawn.


>The user types '50 fd' in the action window and sees an error message that the action was not formatted correctly.
* The user types the action '50 fd' into the action window and presses enter.
* The Parser object parses the action and determines that it is not formatted correctly.
* The Parser object returns an error message to the user indicating that the action was not formatted correctly.
* The CanvasWindow does not update since no new action was successfully parsed.


>The user sets a variable's value and sees it updated in the UI's Variable view.
* The user types a action to set a variable's value into the action window and presses enter.
* The Parser object parses the action and creates a new Command object with the appropriate parameters, including the name and value of the variable to be set.
* The Command object is executed by calling the execute() method on the Environment object, which updates the variable's value.
* The CanvasWindow updates the Variable view to display the updated value of the variable.


>The user sets the pen's color using the UI so subsequent lines drawn when the turtle moves use that color.
* The user selects a new pen color from the UI's Pen Color picker.
* The CanvasWindow creates a new Color object based on the user's selection and sets it as the current pen color.
* The Turtle object is notified of the new pen color and updates its internal state to use the new color for subsequent lines it draws.


>The user types 'repeat 4 [fd 50 rt 90]' in the action window, and sees the turtle move in a square, and the action is added to the environment's history.
* The user types the action 'repeat 4 [fd 50 rt 90]' into the action window and presses enter.
* The Parser object parses the action and creates a new RepeatCommand object with the appropriate parameters.
* The RepeatCommand object executes the specified commands (in this case, 'fd 50' and 'rt 90') the specified number of times (in this case, 4).
* Each Command object is executed by calling the execute() method on the Turtle object, which moves the turtle forward 50 units and turns right 90 degrees.
* The Turtle object notifies any registered observers that it has moved, including the History object, which adds the repeat action and its sub-commands to its history.
* The CanvasWindow updates the display to show the turtle's new position and the lines it has drawn.


>The user types 'clearscreen' in the action window, and sees the turtle return to its starting position, and the action is added to the environment's history.
* The user types the action 'clearscreen' into the action window and presses enter.
* The Parser object parses the action and creates a new Command object with the appropriate parameters.
* The Command object is executed by calling the execute() method on the Turtle object, which moves the turtle back to its starting position and clears the screen.
* The Turtle object notifies any registered observers that it has moved, including the History object, which adds the action to its history.
* The CanvasWindow updates the display to show the turtle at its starting position with a clear screen.


>The user types 'setpensize 5' in the action window and sees the turtle draw thicker lines, and the action is added to the environment's history.
* The user types the action 'setpensize 5' into the action window and presses enter.
* The Parser object parses the action and creates a new Command object with the appropriate parameters.
* The Command object is executed by calling the execute() method on the Turtle object, which sets the pen size to 5.
* The Turtle object notifies any registered observers that its pen size has changed, including the History object, which adds the action to its history.
* The CanvasWindow updates the display to show the turtle drawing thicker lines.


>The user changes the color of the environment's background.
* The user clicks on a palette and choose the color of the background.
* The controller notify a change in the state of the background and notify the background.
* The GUI class switch the background color into the color that the user chooses. 

>The user tries to upload a non-slogo type file
* User clicks ‘Upload File’ button and chooses a file whose type is not .slogo
* Controller throws InvalidFileException
* View catches exception and displays error message informing user to upload an appropriate file

>The user uploads an .slogo file with an invalid action
* User clicks ‘Upload File’ button and chooses an .slogo file
* Controller passes file into FileParser class via getCommandStringList
* FileParser checks if the file is valid (in this case, it is not)
* An InvalidCommandException is thrown
* View catches exception and displayed error message informing which line inside the file caused the error to be triggered

>The user uploads a valid .slogo file
* User clicks ‘Upload File’ button and chooses an .slogo file
* Controller passes file into FileParser class via getCommandStringList
* FileParser will check if the file is valid (which, in this case, it is, so no errors are thrown)
* Controller receives a list of commands, which is then passed into the model in order to be processed and displayed accordingly

>The user changes the color of the background.
* User clicks on the Customization tab of the user interface, prompting the SideView to change what is displayed in that window.
* User clicks on the square for updating a background color, causing the SideView class to reveal a color picker.
* User selects a color, which will prompt the SideView class to call the method “updateBackgroundColor”.

>User speeds up the Turtle animation speed.
* User clicks on the Customization tab of the user interface, prompting the SideView to change what is displayed in that window (if it needs to).
* User slides the “Turtle Speed” slider forward.
* This new speed is sent to the SideView, who will check if it is maximum speed. If it is, it will stop animations. If not, it will just speed up the animation by calling the speedup method.

>User clears the Canvas
* The user clicks on the Broom button on the top right of the Canvas.
* SideView handles this event by telling the Canvas to use the clear function and telling the controller that the turtle location should be reset.
* Controller will tell the model to reset the turtle location.
* SideView will tell Canvas to update the turtle location again and reset the lines.

>User wants to see the examples on the side
* User clicks on one of the examples(images) listed on the side
* The controller fetch the sample code from the sample class and give them to the user and paste on the action view
* The source code appears in the box of the action view
* User clicks on running the source code, the model starts to parse and asks the turtle to follow the action. Turtle begins to move exactly the same as the images 

>User wants to goes back to one of the historical action
* User clears the current action window and clicks one of the History commands.
* The controller fetches the action from the historical commands list, and gives it to the action view and pastes it. 
* Iteratively calls History undo() method
