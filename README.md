## Software Design and Architecture Assignment - Simple Frustration

18/10/2025
- Game coded to basic standard
- Basic Game Rules
- Basic main.java.game scenario 1 passes tests
This is a main.java.game for two players. Red and Blue.
- The main.java.board has 18 positions and 2 Tails (one for each main.java.player) with 3 positions each.
- Each main.java.player starts with a main.java.player piece in their Home position. To win the main.java.game, a main.java.player must get their main.java.player piece to their End position first.
- Players take turns to roll 2 x 6-sided main.java.dice.
- They move clockwise around the main.java.board based on the number of the main.java.dice roll.
- The Red main.java.player moves clockwise through positions 1 (Home), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, R1, R2, R3 (End).
- The Blue main.java.player moves clockwise through positions 10 (Home), 11, 12, 13, 14, 15, 16, 17, 18, 1, 2, 3, 4, 5, 6, 7, 8, 9, B1, B2, B3 (End)
- If a main.java.player piece lands on a position occupied by another main.java.player piece, then this is called a Hit. The basic main.java.game allows for multiple players to occupy the same position.
- The main.java.game continues until either Red or Blue have reached their End position. This could be done by throwing a main.java.dice roll that lands the main.java.player on or beyond the End position. Throwing a main.java.dice roll that would move the main.java.player
  beyond the End position is called an Overshoot.

TODO::
- Figure out how to get my pieces to have different homes and still follow the same main.java.board pattern
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

Strategy Pattern used to support single-die, double-die, and fixed main.java.dice behaviour.

- Game
  - Responsibility:
    - Controls turn order 
    - Knows when the main.java.game starts/ends 
    - Delegates work (does NOT do the work itself)
  - Should:
    - Alternate players 
    - Ask main.java.dice for a roll 
    - Ask main.java.player to move 
    - Check for win condition
- Player
  - Responsibility:
    - Represents a single main.java.player 
    - Owns its position and turn count 
    - Knows its colour and home/end logic
- Board
  - Responsibility:
    - Represents the main.java.board layout 
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
    - Decide what happens when a main.java.player would land on another main.java.player

Game = turn control
Board = movement rules
Player = state
DiceShaker = randomness
WinCondition = winning logic
HitRule = collision logic

Domain logic should not depend on IO: seperate the console reporter from the main.java.game class

“The original implementation used a boolean flag (exactLanding) to control win behaviour.
This was refactored into a WinCondition abstraction using polymorphism.
This removes conditional logic, improves extensibility, and adheres to the Open/Closed Principle.”

Strategy pattern → DiceShaker
Open/Closed Principle → WinCondition & HitRule
Single Responsibility → Game vs Board vs Player
Testability → FixedDiceShaker

The main.java.dice variation was implemented using the Strategy pattern.
The Game class depends only on the DiceShaker interface, allowing different main.java.dice behaviours (single, double, fixed) to be substituted without modification.
This supports extensibility and testability.

4-main.java.player
This directly demonstrates:
Maintainable multi-class design
Open/Closed Principle
Scalability

The project follows Clean Architecture principles.
Core main.java.game logic is independent of frameworks and is located in the domain and use-case layers.
Spring Boot is used exclusively for dependency injection and main.java.application assembly within the main.java.application layer.
This ensures that domain logic remains fully testable and decoupled from infrastructure concerns.

Spring Boot is used as a Dependency Injection container to assemble the main.java.application.
The core main.java.game logic is framework-agnostic and resides outside the Spring context, ensuring full testability and adherence to Clean Architecture principles.