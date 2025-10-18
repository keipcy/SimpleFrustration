package test.game;

import board.Board;
import org.junit.jupiter.api.Assertions;
import player.Player;
import util.MoveResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameScenarioTest {
    static Stream<TestScenario> provideScenarios() {
        return Stream.of(
                ScenarioData.basicScenario1ex1(),
                ScenarioData.basicScenario1ex2()
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideScenarios")
    void testScenario(TestScenario scenario) {
        Board board = new Board(18, 3);
        Player red = new Player("red");
        Player blue = new Player("blue");
        MockDiceRoller dice = new MockDiceRoller();
        dice.addRolls(scenario.getDiceRolls().stream().mapToInt(Integer::intValue).toArray());

        Player current = red;
        MoveResult result = null;

        for(int roll : scenario.getDiceRolls()) {
            result = board.movePlayer(current, roll);

            if(result.isWin()) break;
            current = (current == red) ? blue : red;
            }

        assertNotNull(result, "Game must produce a result");
        assertEquals(scenario.getExpectedWinner().toLowerCase(),
                current.getColour().toLowerCase());
        }
    }
