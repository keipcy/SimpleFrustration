## Software Design and Architecture Assignment - Simple Frustration

18/10/2025
- Game coded to basic standard
- Basic Game Rules
- Basic game scenario 1 passes tests
This is a game for two players. Red and Blue.
- The board has 18 positions and 2 Tails (one for each player) with 3 positions each.
- Each player starts with a player piece in their Home position. To win the game, a player must get their player piece to their End position first.
- Players take turns to roll 2 x 6-sided dice.
- They move clockwise around the board based on the number of the dice roll.
- The Red player moves clockwise through positions 1 (Home), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, R1, R2, R3 (End).
- The Blue player moves clockwise through positions 10 (Home), 11, 12, 13, 14, 15, 16, 17, 18, 1, 2, 3, 4, 5, 6, 7, 8, 9, B1, B2, B3 (End)
- If a player piece lands on a position occupied by another player piece, then this is called a Hit. The basic game allows for multiple players to occupy the same position.
- The game continues until either Red or Blue have reached their End position. This could be done by throwing a dice roll that lands the player on or beyond the End position. Throwing a dice roll that would move the player
  beyond the End position is called an Overshoot.

TODO::
- Figure out how to get my pieces to have different homes and still follow the same board pattern
- Include test scenarios 2 and 3
- Plan for variations


The variations and advanced features attempted and their design.
• An explanation of where and why design patterns have been used (naming the
design pattern).
• An explanation of where and why SOLID principles have been followed (naming
the principle).
• An explanation of how you have applied clean or “ports and adapters”
architecture with reference to dependencies  
• An evaluation of your implementation.

Strategy Pattern used to support single-die, double-die, and fixed dice behaviour.

- Game
  - Responsibility:
    - Controls turn order 
    - Knows when the game starts/ends 
    - Delegates work (does NOT do the work itself)
  - Should:
    - Alternate players 
    - Ask dice for a roll 
    - Ask player to move 
    - Check for win condition
- Player
  - Responsibility:
    - Represents a single player 
    - Owns its position and turn count 
    - Knows its colour and home/end logic
- Board
  - Responsibility:
    - Represents the board layout 
    - Calculates movement 
    - Knows tail positions & wraparound rules
  - Should:
    - “If Red is on X and rolls Y, where do they land?” 
    - “Is this position in the tail?” 
    - “Is this an overshoot?”
- Win Condition
  - Responsibility:
    - Decide if a move results in a win
- Hit rule
  - Responsibility:
    - Decide what happens when a player would land on another player

Game = turn control
Board = movement rules
Player = state
DiceShaker = randomness
WinCondition = winning logic
HitRule = collision logic

Domain logic should not depend on IO: seperate the console reporter from the game class

“The original implementation used a boolean flag (exactLanding) to control win behaviour.
This was refactored into a WinCondition abstraction using polymorphism.
This removes conditional logic, improves extensibility, and adheres to the Open/Closed Principle.”

Strategy pattern → DiceShaker
Open/Closed Principle → WinCondition & HitRule
Single Responsibility → Game vs Board vs Player
Testability → FixedDiceShaker

The dice variation was implemented using the Strategy pattern.
The Game class depends only on the DiceShaker interface, allowing different dice behaviours (single, double, fixed) to be substituted without modification.
This supports extensibility and testability.

4-player
This directly demonstrates:
Maintainable multi-class design
Open/Closed Principle
Scalability