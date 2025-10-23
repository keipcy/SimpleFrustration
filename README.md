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
