package test.game;

import java.util.List;

public class TestScenario {
    private final String colour;
    private final List<Integer> diceRolls;
    private final String expectedWinner;

    public TestScenario(String colour, List<Integer> diceRolls, String expectedWinner) {
        this.colour = colour;
        this.diceRolls = diceRolls;
        this.expectedWinner = expectedWinner;
    }

    public String getColour(){
        return colour;
    }

    public List<Integer> getDiceRolls() {
        return diceRolls;
    }

    public String getExpectedWinner() {
        return expectedWinner;
    }
}
