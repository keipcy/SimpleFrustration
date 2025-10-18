import board.Board;
import dice.DiceShaker;
import dice.RandomDoubleDiceShaker;
import player.Player;
import util.MoveResult;

public class Game {
    private final Board board;
    private final Player blue;
    private final Player red;
    private final DiceShaker shaker;
    private Player currentPlayer;

    public Game() {
        this.board = new Board(18, 3);
        this.shaker = new RandomDoubleDiceShaker();
        this.blue = new Player("blue");
        this.red = new Player("red");
        board.addPlayer(red, 1);    // Red Home = 1
        board.addPlayer(blue, 10);  // Blue Home = 10
        this.currentPlayer = red;
    }

    public void start() {
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("\n" + currentPlayer.getColour() + "'s turn.");
            int roll = shaker.shake();
            System.out.println(currentPlayer.getColour() + " rolled " + roll);

            MoveResult result = board.movePlayer(currentPlayer, roll);
            System.out.println(result.getMessage());

            if(result.isWin()) {
                System.out.println(currentPlayer.getColour() + " wins!");
                gameOver = true;
            } else {
                switchTurn();
            }
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == blue) ? red : blue;
    }
}
