import frustration.dice.FixedDiceShaker;
import frustration.game.Game;
import frustration.game.GameConfiguration;
import frustration.hitrule.ForfeitOnHitRule;
import frustration.hitrule.IgnoreHitRule;
import org.junit.jupiter.api.Test;
import frustration.ports.GameOutput;
import frustration.wincondition.ExactLandingWinCondition;
import frustration.wincondition.OvershootAllowedWinCondition;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private final GameOutput out = message -> { };

    @Test
    public void blueWinsBasic() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(12, 12, 7, 8));
        Game game = new Game(
                shaker,
                new OvershootAllowedWinCondition(),
                new IgnoreHitRule(),
                GameConfiguration.basicTwoPlayer(),
                out
        );
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void redWinsBasic() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(8, 2, 3, 4, 9));
        Game game = new Game(
                shaker,
                new OvershootAllowedWinCondition(),
                new IgnoreHitRule(),
                GameConfiguration.basicTwoPlayer(),
                out
        );
        game.start();
        assertEquals("red", game.getWinnerColour());
    }

    @Test
    public void blueWinsOvershootBasic() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(12, 12, 7, 11));
        Game game = new Game(
                shaker,
                new OvershootAllowedWinCondition(),
                new IgnoreHitRule(),
                GameConfiguration.basicTwoPlayer(),
                out
        );
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void blueWinsSingleDiceVariation() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(6, 6, 6, 6, 3, 4, 3, 4));
        Game game = new Game(
                shaker,
                new OvershootAllowedWinCondition(),
                new IgnoreHitRule(),
                GameConfiguration.basicTwoPlayer(),
                out
        );
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void redWinsLandsExactly() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(12, 12, 12, 9, 8));
        Game game = new Game(
                shaker,
                new ExactLandingWinCondition(),
                new IgnoreHitRule(),
                GameConfiguration.basicTwoPlayer(),
                out
        );
        game.start();
        assertEquals("red", game.getWinnerColour());
    }

    @Test
    public void blueWinsHITRule() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(8, 2, 3, 12, 9, 6));
        Game game = new Game(
                shaker,
                new ExactLandingWinCondition(),
                new ForfeitOnHitRule(),
                GameConfiguration.basicTwoPlayer(),
                out
        );
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void largeBoardTest() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(7,3,8,5,7,6,8,7,6,8,2,4,4,8,5,7,8,3,9,9,7,5,7,9));
        Game game = new Game(
                shaker,
                new OvershootAllowedWinCondition(),
                new IgnoreHitRule(),
                GameConfiguration.largeFourPlayer(),
                out
        );
        game.start();
        assertEquals("yellow", game.getWinnerColour());
    }

    @Test
    public void largeBoardVariations() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(11,11,8,10,10,7,2,4,6,8,4,9,9,10,7,11,10,8,5,7));
        Game game = new Game(
                shaker,
                new ExactLandingWinCondition(),
                new ForfeitOnHitRule(),
                GameConfiguration.largeFourPlayer(),
                out
        );
        game.start();
        assertEquals("yellow", game.getWinnerColour());
    }
}
