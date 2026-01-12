package game;

import board.Board;
import board.BoardConfig;
import dice.DiceShaker;
import dice.RandomDoubleDiceShaker;
import hitrule.HitRule;
import hitrule.IgnoreHitRule;
import player.Player;
import player.Player.MoveResult;
import util.Ansi;
import wincondition.WinCondition;
import wincondition.OvershootAllowedWinCondition;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private int currentPlayerIndex;
    private final DiceShaker shaker;
    private final WinCondition winCondition;
    private Player winner;
    private final HitRule hitRule;

    public Game() {
        this(
                new RandomDoubleDiceShaker(),
                new OvershootAllowedWinCondition(),
                new IgnoreHitRule()
        );
    }

    public Game(DiceShaker shaker, WinCondition winCondition, HitRule hitRule) {
        this(shaker, winCondition, hitRule, GameConfiguration.basicTwoPlayer());
    }

    public Game(
            DiceShaker shaker,
            WinCondition winCondition,
            HitRule hitRule,
            GameConfig config
    ) {
        this.shaker = shaker;
        this.winCondition = winCondition;
        this.hitRule = hitRule;
        this.board = new Board(config.boardConfig());

        this.players = new ArrayList<>();
        for (var pc : config.players()) {
            players.add(new Player(
                    pc.colour(),
                    board.buildTrackForStart(pc.startTile(), pc.tailPrefix())
            ));
        }

        this.currentPlayerIndex = 0;
    }

    public void start() {
        boolean gameOver = false;
        int round = 1;

        while (!gameOver) {
            Player current = players.get(currentPlayerIndex);
            String plainName = current.getColour();
            String colorCode = switch (plainName.toLowerCase()) {
                case "red" -> Ansi.RED;
                case "blue" -> Ansi.BLUE;
                case "green" -> Ansi.GREEN;
                case "yellow" -> Ansi.YELLOW;
                default -> Ansi.RESET;
            };

            System.out.println("\n" + Ansi.color(plainName + "'s turn!", colorCode));

            int roll = shaker.shake();
            System.out.println(Ansi.color("Rolled: " + roll, colorCode));

            List<Player> others = players.stream()
                    .filter(p -> p != current)
                    .toList();

            MoveResult result = board.calculateMove(
                    current,
                    others,
                    roll,
                    winCondition,
                    hitRule
            );
            current.applyMove(result);

            String beforeRaw = result.from();
            String afterRaw = result.to();

            if (result.overshot()) {
                System.out.println(Ansi.color(
                        plainName + " overshot and forfeits the turn (position unchanged: " + result.from() + ")",
                        colorCode
                ));
            } else {
                System.out.println(Ansi.color(
                        plainName + " moved from " + result.from() + " to " + result.to(),
                        colorCode
                ));
            }

            // per-round summary (entire line coloured)
            String roundSummary = "Round " + round + ": " + plainName + " rolled " + roll
                    + " — position before: " + result.from()
                    + ", position after: " + result.to();
            System.out.println(Ansi.color(roundSummary, colorCode));

            if (result.won()) {
                System.out.println(Ansi.color(plainName + " WINS THE GAME", colorCode));
                winner = current;
                gameOver = true;
            } else {
                switchTurn();
                round++;
            }
        }
    }

    private void switchTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public String getWinnerColour() {
        return (winner == null) ? null : winner.getColour();
    }
}