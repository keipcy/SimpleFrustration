package game;

import board.BoardConfig;
import player.PlayerConfig;

import java.util.List;

public record GameConfig(
        BoardConfig boardConfig,
        List<PlayerConfig> players
) {}