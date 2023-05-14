# Collections API Lab Discussion
### Trevon Helm (tmh85), Jay Yoon (jy320), Rodrigo Bassi Guerreiro (rb419), Andy Wang (xw214)
### TEAM 02



## In your experience using these collections, are they hard or easy to use?
* Relatively easy to use, but it has many inheritances though

## In your experience using these collections, do you feel mistakes are easy to avoid?
* Some syntax confusions exist (ex. when to use empty or isEmpty to check whether collection is empty)

## What methods are common to all collections (except Maps)?
* two main categories may include add and remove functionalities (addAll, add, remove, removeAll)
* other functions may include  frequency, get, max, min, replaceAll, etc.

## What methods are common to all Deques?
* adding and removing functionalities include addFirst, pullLast, removeFirst, peekFirst

## What is the purpose of each interface implemented by LinkedList?
* LinkedList is implementation of List and Deque interfaces
* Both are needed as you need a list of nodes

## How many different implementations are there for a Set?
* Some include TreeSet, HashSet, and LinkedHashSet
* These are the three general purpose implementations of Set

## What is the purpose of each superclass of PriorityQueue?
* The superclasses are Abstract queue, Abstract Collection, Object
* PriorityQueue is a queue, which is a collection, and all classes are objects

## What is the purpose of the Collections utility class?
* To be able to implement the general functions of Collections (adding, sorting, removing elements)



## API Characterics applied to Collections API

* Easy to learn: names are intuitive (they reflect what they actually do)

* Encourages extension: support multiple inheritances, and containing the most general functionalities that can be implemented in different contexts

* Leads to readable code: the code is straightforward

* Hard to misuse: There are some methods that are oddly specific to certain types of collections, like checkedMap, that seemingly could be used for not maps, or just not that specific data structure.
