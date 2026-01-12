package game;

import board.BoardConfig;
import player.PlayerConfig;

import java.util.List;

public class GameConfiguration {

    public static GameConfig basicTwoPlayer() {
        return new GameConfig(
                new BoardConfig(18, 3),
                List.of(
                        new PlayerConfig("red", 1, "R"),
                        new PlayerConfig("blue", 10, "B")
                )
        );
    }

    public static GameConfig largeFourPlayer() {
        return new GameConfig(
                new BoardConfig(36, 6),
                List.of(
                        new PlayerConfig("red", 1, "R"),
                        new PlayerConfig("blue", 10, "B"),
                        new PlayerConfig("green", 19, "G"),
                        new PlayerConfig("yellow", 28, "Y")
                )
        );
    }
}