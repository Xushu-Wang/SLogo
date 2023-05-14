# Cell Society API Lab Discussion
### Jay Yoon (jy320), Rodrigo Bassi Guerreiro (rb419), Trevon Helm (tmh85), Andy Wang(xw214)
### TEAM 02


## Current Simulation API

### External

* Identified Classes/Methods: initiateSimulation() method

* Goals: hiding grid structure from user while providing all necessary information in an immutable manner except for user’s intentional changes (specifics would depend on perspective: whether from View or Controller)

* Abstractions: relevant abstractions would include Cell abstraction

* Services and their Contract: the method would return a collection of Cells, so any assigned variable would have to follow specified return type


### Internal

* Identified Classes/Methods: getNextType() method in Cell class

* Goals: being used in deciding the internal process of same packages (ex. Using cell’s next type to determine the correct rule applications)

* Abstractions: relevant abstractions would include Cell abstraction

* Services and their Contract: the method would correctly returns a valid next cell type to be stored as an integer variable



## Wish Simulation API

### External: getCellGUIs()

* Goals: to be detached from the rule logic (see what the cell display are without being required to manipulate specifics of rules)

* Abstractions: CellShape abstractions would enable different shape displays

* Services and their Contract: the method correctly returns a list of valid cell shape type


### Internal: getCellState()

* Goals: to encapsulate specifics of the cell state (without any information about how it is updated)

* Abstractions: Cell abstraction containing the valid cell state

* Services and their Contract: the method correctly returns a valid cell state that can be stored in a variable
