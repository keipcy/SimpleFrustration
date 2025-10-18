package test.game;

import dice.DiceShaker;

import java.util.LinkedList;
import java.util.Queue;

public class MockDiceRoller implements DiceShaker {
    private final Queue<Integer> predefinedRolls = new LinkedList<>();

    public void addRolls(int... rolls) {
        for(int r : rolls) predefinedRolls.add(r);
    }

    @Override
    public int shake() {
        if(predefinedRolls.isEmpty()) {
            throw new IllegalStateException("No predefined rolls left!");
        }
        return predefinedRolls.poll();
    }
}
