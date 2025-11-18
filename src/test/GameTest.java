package test;

import game.Game;
import dice.FixedDiceShaker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void blueWinsBasic() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(12,12,7,8)); // red wins immediately
        Game game = new Game(shaker);
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void redWinsBasic() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(8,2,3,4,9));
        Game game = new Game(shaker);
        game.start();
        assertEquals("red", game.getWinnerColour());
    }

    @Test
    public void blueWinsOvershootBasic() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(12,12,7,11));
        Game game = new Game(shaker);
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void blueWinsSingleDiceVariation() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(6,6,6,6,3,4,3,4));
        Game game = new Game(shaker);
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }

    @Test
    public void redWinsLandsExactlyHITTrue() {
        FixedDiceShaker shaker = new FixedDiceShaker(Arrays.asList(6,6,6,6,3,4,3,4));
        Game game = new Game(shaker);
        game.start();
        assertEquals("blue", game.getWinnerColour());
    }
}
