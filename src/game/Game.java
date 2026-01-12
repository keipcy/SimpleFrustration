package game;

import board.Board;
import dice.DiceShaker;
import dice.RandomDoubleDiceShaker;
import player.Player;
import player.Player.MoveResult;
import util.Ansi;

public class Game {
    private final Board board;
    private final Player red;
    private final Player blue;
    private Player current;
    private final DiceShaker shaker;
    private final boolean exactLanding;
    private Player winner;

    // per-player turn counters
    private int redTurns = 0;
    private int blueTurns = 0;

    public Game() {
        this(new RandomDoubleDiceShaker(), false);
    }

    public Game(DiceShaker shaker) {
        this(shaker, false);
    }

    public Game(DiceShaker shaker, boolean exactLanding) {
        this.shaker = shaker;
        this.exactLanding = exactLanding;
        this.board = new Board(18, 3);

        this.red = new Player("red", board.buildTrackForStart(1));
        this.blue = new Player("blue", board.buildTrackForStart(10));

        this.current = red;
        this.winner = null;
    }

    public void start() {
        boolean gameOver = false;
        int round = 1;

        while (!gameOver) {
            String plainName = current.getColour();
            String colorCode = plainName.equals("red") ? Ansi.RED : Ansi.BLUE;

            // count the turn for the current player
            if (current == red) {
                redTurns++;
            } else {
                blueTurns++;
            }

            System.out.println("\n" + Ansi.color(plainName + "'s turn!", colorCode));

            // capture position before the roll
            String beforeRaw = current.getPositionLabel();

            int roll = shaker.shake();
            System.out.println(Ansi.color("Rolled: " + roll, colorCode));

            // use tryMove so exactLanding mode is respected and we can detect overshoot
            MoveResult result = current.tryMove(roll, exactLanding);

            // position after the move
            String afterRaw = current.getPositionLabel();

            if (result == MoveResult.OVERSHOT) {
                System.out.println(Ansi.color(plainName + " overshot and forfeits the turn (position unchanged: " + beforeRaw + ")", colorCode));
            } else {
                System.out.println(Ansi.color(plainName + " moved to " + afterRaw, colorCode));
            }

            // per-round summary (entire line coloured)
            String roundSummary = "Round " + round + ": " + plainName + " rolled " + roll
                    + " — position before: " + beforeRaw + ", position after: " + afterRaw;
            System.out.println(Ansi.color(roundSummary, colorCode));

            if (result == MoveResult.WON) {
                System.out.println(Ansi.color(plainName + " WINS THE GAME", colorCode));
                winner = current;
                gameOver = true;
            } else {
                switchTurn();
                round++;
            }
        }

        // print totals at end of game
        System.out.println();
        System.out.println(Ansi.color("Total turns - red: " + redTurns, Ansi.RED));
        System.out.println(Ansi.color("Total turns - blue: " + blueTurns, Ansi.BLUE));
    }

    private void switchTurn() {
        current = (current == red) ? blue : red;
    }

    public String getWinnerColour() {
        return (winner == null) ? null : winner.getColour();
    }
}