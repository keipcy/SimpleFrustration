// java
// src/Main.java
import game.Game;
import dice.RandomDoubleDiceShaker;
import dice.RandomSingleDiceShaker;
import dice.DiceShaker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Choose game mode:");
        System.out.println("1) 1-die (single die)");
        System.out.println("2) basic (two dice)");
        System.out.println("3) exact landing on tail-3 (must land exactly to win)");
        System.out.print("Enter 1, 2 or 3: ");

        String choice = in.nextLine().trim();

        DiceShaker shaker;
        boolean exactLanding = false;

        if (choice.equals("1") || choice.equalsIgnoreCase("1-die") || choice.equalsIgnoreCase("single")) {
            shaker = new RandomSingleDiceShaker();
            System.out.println("Starting 1-die variation.\n");
        } else if (choice.equals("3") || choice.equalsIgnoreCase("exact") || choice.toLowerCase().contains("exact")) {
            shaker = new RandomDoubleDiceShaker();
            exactLanding = true;
            System.out.println("Starting exact-landing (tail-3) variation.\n");
        } else {
            shaker = new RandomDoubleDiceShaker();
            System.out.println("Starting basic two-dice variation.\n");
        }

        Game game = new Game(shaker, exactLanding);
        game.start();
    }
}
