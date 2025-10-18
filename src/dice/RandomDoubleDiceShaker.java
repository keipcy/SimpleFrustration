package dice;

public class RandomDoubleDiceShaker implements DiceShaker {
    private final DiceShaker shaker = new RandomSingleDiceShaker();

    @Override
    public int shake() {
        return shaker.shake() + shaker.shake();
    }
}
