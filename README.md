# snake
Classic snake game. Snake moves on the screen and grows by eating food, and dies if its head hits wall or itself. 
New food is generated randomly on available spots. Direction of movement is controled by arrow keys.

GUI was implemented with Javafx.

GUI
  |-- Main.java
  |-- Painter.java
  
Game Logic
  |-- GameLoop.java
  |-- Snake.java
  |-- Board.java
  |-- Point.java
  |-- RandomizedSet.java
  
RandomizedSet is a data structure that supports insert, remove and getRandom in average O(1) time. New food is randomly picked from all available spots on the game board. A RamdonizedSet is used to store those available spots. The set is updated via inserting or removing element when snake moves.   
