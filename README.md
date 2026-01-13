# Software Design and Architecture Assignment – Simple Frustration

## Introduction

Simple Frustration is a turn-based board game driven by dice rolls. The game is played on a circular board where two or four players each have a designated home position and a private tail. On their turn, a player rolls dice and moves forward by the resulting number of spaces. After completing a full circuit of the board, a player enters their tail, and the first player to reach the end of their tail wins the game.

The game supports a number of rule variations. For example, landing on another player may result in a “hit”, causing the active player to forfeit their move. Other variations affect dice behaviour and winning conditions. These variations significantly impact gameplay and therefore present an ideal problem space for exploring extensible software design.

This project is implemented as a Spring Boot application. The design prioritises extensibility, testability, and clear separation of concerns, and adheres to Clean Architecture principles. Rather than focusing solely on implementing game rules, the primary challenge of this assignment was designing an architecture that supports variation and extension without requiring modification to existing core logic. This README explains how those design goals were achieved and evaluates the resulting implementation.

## Functional Coverage

This implementation covers the full functional specification of the base game and a selected set of variations. Variations were implemented iteratively to ensure correctness and maintainability at each stage, allowing the core game logic to remain stable as new features were introduced. This process was supported through JUnit testing.

## Basic Game

The basic game represents the baseline behaviour from which all variations were developed. It consists of two players on a standard circular board with 18 positions and a tail of length 3. By default, the game is played using two dice, and overshooting the final tail position still constitutes a win.

### Rule Variations

Several rule families were implemented as optional variations:

- Dice behaviour can be configured to use different dice strategies without modifying gameplay logic.

- Winning conditions were separated from the game loop to support both overshoot-allowed and exact-landing rules.

- Hit behaviour was abstracted to support rules such as ignoring hits or forfeiting a turn, with the potential for future extensions.

At runtime, players can select which rule variations to apply, allowing different combinations of game behaviour without changes to the core implementation.

#### Four-Player Board

The four-player variation extends the base game to support four players on a larger board consisting of 36 positions, with each player having a tail of length 6. All previously implemented rule variations remain compatible with this configuration.

Save/replay functionality and explicit game state machines were intentionally excluded to prioritise architectural clarity and correctness of the core domain model. These features would be natural extensions given the current separation of concerns.

Importantly, this variation reuses the same game loop and rule implementations, demonstrating that the architecture scales through configuration rather than branching logic.

## Design Patterns

The primary design challenge in this project was handling variation. Without careful design, supporting multiple rule combinations would lead to conditional logic scattered throughout the game loop, increasing coupling and reducing maintainability.

To address this, the Strategy pattern was used to encapsulate interchangeable game rules behind stable interfaces. Strategy is applied explicitly through the DiceShaker, WinCondition, and HitRule abstractions. Each represents a family of related behaviour, with concrete implementations defining specific rule variants.

This replaces conditional logic with polymorphism, allowing new rules to be introduced without modifying existing classes. As a result, the core game loop remains unchanged as new behaviour is added, directly supporting the Open/Closed Principle.

Strategy is particularly appropriate in this context because dice behaviour, win conditions, and hit resolution vary independently of one another. Encapsulating these concerns behind interfaces reduces coupling and allows behaviour to be selected through configuration rather than code modification.

## Ports and Adapters

Ports and Adapters (Hexagonal Architecture) is an architectural style that isolates domain logic from external concerns such as input/output mechanisms and frameworks.

In this project, GameOutput acts as a port that defines how game output is communicated, while ConsoleGameOutput is an adapter that implements this port using standard console output. The core game logic depends only on the GameOutput interface and is therefore unaware of how or where output is produced.

This design ensures that the domain layer has no dependency on console output or the Spring Boot framework. As a result, the game logic remains testable in isolation and can be reused with alternative output mechanisms, such as file logging or a graphical user interface, without requiring changes to the domain or use case code.

## Factory and Configuration

Game setup and configuration are centralised using the GameConfig abstraction and the GameConfiguration factory-style class. These classes encapsulate all information required to construct a valid game instance, including board size, player count, starting positions, and tail configuration.

By separating configuration from the core game logic, the menu and application startup code can assemble different game variants without introducing conditional logic into the domain layer. This approach resembles a simple Factory combined with configuration objects, allowing object graphs to be assembled externally rather than within gameplay code.

As a result, new game modes can be introduced by defining new configurations rather than modifying existing classes.

## SOLID Principles

SOLID is an acronym representing five principles of object-oriented design that aim to improve maintainability, extensibility, and robustness. These principles guided class responsibility and dependency management throughout the project.

### Single Responsibility Principle (SRP)

Each class has a clearly defined responsibility. The Game class orchestrates turn progression and gameplay flow. The Board class handles movement and position calculation. Rule classes are responsible solely for rule evaluation, and output logic is isolated within adapters. This separation reduces the risk of unrelated changes impacting core behaviour.

### Open/Closed Principle (OCP)

The system is open for extension but closed for modification. New rules are introduced through new implementations of existing interfaces rather than changes to the game loop or board logic. For example, adding a new winning rule requires only a new WinCondition implementation.

### Interface Segregation Principle (ISP)

Interfaces are small and focused, exposing only behaviour required by their clients. Examples include DiceShaker, HitRule, and GameOutput. This avoids forcing implementations to depend on unused methods.

### Liskov Substitution Principle (LSP)

All implementations of a given interface are fully substitutable. Any WinCondition or DiceShaker implementation can be used by the game without altering assumptions or behaviour.

### Dependency Inversion Principle (DIP)

High-level modules depend on abstractions rather than concrete implementations. The Game class depends on interfaces rather than specific rule or output implementations. Infrastructure code depends on domain logic, not the reverse.

## Clean Architecture

The application follows Clean Architecture principles by separating the system into domain, application, and infrastructure layers.

The domain layer contains all core game logic and has no dependency on Spring Boot or I/O. The application layer coordinates use cases such as assembling game configurations and starting gameplay. The infrastructure layer contains adapters for console output and Spring Boot dependency injection.

Dependencies flow inward: infrastructure depends on application logic, and application logic depends on the domain, but never the reverse. This ensures that changes to frameworks or output mechanisms do not impact core gameplay logic.

## Testing Strategy

JUnit tests were used throughout development to verify correctness as functionality was added. Tests focus exclusively on domain and use case logic and do not depend on Spring Boot or console output.

Deterministic test doubles, such as FixedDiceShaker, were used to control dice outcomes and ensure predictable game progression. A test implementation of GameOutput was used where required to avoid infrastructure dependencies during testing. This approach improves reliability and supports safe iteration when introducing new variations.

## Evaluation

Development began with establishing a fully working base game supported by automated testing. Variations were then introduced incrementally, following an iterative approach. Writing tests alongside each feature provided confidence that existing behaviour was preserved and allowed issues to be identified early.

Applying SOLID principles during development encouraged decisions that supported future extension. Encapsulating hit behaviour behind the HitRule interface, for example, allowed multiple rule variants to be supported without introducing conditional logic into the core game flow.

With additional time, further design patterns such as Decorator or Observer could be explored to support features such as event-driven updates or state transitions. These patterns were considered but intentionally excluded to prioritise clarity and correctness of the core architecture.

## Conclusion

This project demonstrates how careful application of software design principles enables a flexible and testable implementation of a rule-based game. By prioritising clean architecture and variation handling, the resulting system is well-suited for extension and continued development while remaining robust and maintainable.