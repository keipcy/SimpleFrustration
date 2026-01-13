package frustration.application;

import frustration.dice.DiceShaker;
import frustration.game.Game;
import frustration.game.GameConfig;
import frustration.hitrule.HitRule;
import frustration.ports.GameOutput;
import frustration.wincondition.WinCondition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Scanner;

@Configuration
public class GameRunner implements CommandLineRunner {

    private final Map<String, DiceShaker> dice;
    private final Map<String, WinCondition> wins;
    private final Map<String, HitRule> hits;
    private final Map<String, GameConfig> configs;
    private final GameOutput out;

    public GameRunner(
            Map<String, DiceShaker> dice,
            Map<String, WinCondition> wins,
            Map<String, HitRule> hits,
            Map<String, GameConfig> configs,
            GameOutput out
    ) {
        this.dice = dice;
        this.wins = wins;
        this.hits = hits;
        this.configs = configs;
        this.out = out;
    }

    @Override
    public void run(String... args) {
        Scanner in = new Scanner(System.in);

        // Step 1: choose players
        out.println("Choose player mode:");
        out.println("1) 2 players");
        out.println("2) 4 players");
        out.println("Enter 1 or 2:");
        String pChoice = in.nextLine().trim();

        GameConfig config = pChoice.equals("2")
                ? configs.get("fourPlayer")
                : configs.get("twoPlayer");

        // Step 2: choose rules
        out.println("\nChoose rules:");
        out.println("1) Basic (double dice, overshoot win, ignore hits)");
        out.println("2) Exact landing (double dice, exact win, ignore hits)");
        out.println("3) Hit rule (double dice, overshoot win, forfeit on hit)");
        out.println("4) Single dice (single dice, overshoot win, ignore hits)");
        out.println("Enter 1-4:");
        String rChoice = in.nextLine().trim();

        DiceShaker shaker = dice.get("double");
        WinCondition win = wins.get("overshoot");
        HitRule hit = hits.get("ignoreHit");

        switch (rChoice) {
            case "2" -> win = wins.get("exact");
            case "3" -> hit = hits.get("forfeitHit");
            case "4" -> shaker = dice.get("single");
            default -> { /* keep basic */ }
        }

        Game game = new Game(shaker, win, hit, config, out);
        game.start();
    }
}