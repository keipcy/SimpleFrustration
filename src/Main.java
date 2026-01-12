import game.Game;
import game.GameConfig;
import game.GameConfiguration;
import dice.*;
import hitrule.*;
import wincondition.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // ─── STEP 1: BOARD / PLAYERS ──────────────────────────────
        GameConfig config;

        System.out.println("Choose number of players:");
        System.out.println("1) 2 players (small board)");
        System.out.println("2) 4 players (large board)");
        System.out.print("Enter 1 or 2: ");

        String playerChoice = in.nextLine().trim();

        switch (playerChoice) {
            case "2" -> {
                config = GameConfiguration.largeFourPlayer();
                System.out.println("Selected 4 players.\n");
            }
            case "1" -> {
                config = GameConfiguration.basicTwoPlayer();
                System.out.println("Selected 2 players.\n");
            }
            default -> {
                config = GameConfiguration.basicTwoPlayer();
                System.out.println("Invalid choice. Defaulting to 2 players.\n");
            }
        }

        // ─── STEP 2: DICE RULE ───────────────────────────────────
        DiceShaker shaker;

        System.out.println("Choose dice rule:");
        System.out.println("1) Single die");
        System.out.println("2) Two dice");
        System.out.print("Enter 1 or 2: ");

        if (in.nextLine().trim().equals("1")) {
            shaker = new RandomSingleDiceShaker();
            System.out.println("Using single die.\n");
        } else {
            shaker = new RandomDoubleDiceShaker();
            System.out.println("Using two dice.\n");
        }

        // ─── STEP 3: WIN CONDITION ────────────────────────────────
        WinCondition winCondition;

        System.out.println("Choose win rule:");
        System.out.println("1) Overshoot allowed");
        System.out.println("2) Exact landing on END");
        System.out.print("Enter 1 or 2: ");

        if (in.nextLine().trim().equals("2")) {
            winCondition = new ExactLandingWinCondition();
            System.out.println("Exact landing required.\n");
        } else {
            winCondition = new OvershootAllowedWinCondition();
            System.out.println("Overshoot allowed.\n");
        }

        // ─── STEP 4: HIT RULE ─────────────────────────────────────
        HitRule hitRule;

        System.out.println("Choose hit rule:");
        System.out.println("1) Ignore hits");
        System.out.println("2) Forfeit move on hit");
        System.out.print("Enter 1 or 2: ");

        if (in.nextLine().trim().equals("2")) {
            hitRule = new ForfeitOnHitRule();
            System.out.println("Forfeit on hit enabled.\n");
        } else {
            hitRule = new IgnoreHitRule();
            System.out.println("Hits ignored.\n");
        }

        // ─── START GAME ───────────────────────────────────────────
        Game game = new Game(shaker, winCondition, hitRule, config);
        game.start();
    }
}
