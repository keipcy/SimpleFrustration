package dice;

import java.util.List;

public class FixedDiceShaker implements DiceShaker {
    private final List<Integer> rolls;
    private int current = 0;

    public FixedDiceShaker(List<Integer> rolls) {
        this.rolls = rolls;
    }

    @Override
    public int shake() {
        if (current >= rolls.size()) {
            throw new RuntimeException("No more dice rolls in sequence");
        }
        return rolls.get(current++);
    }
}