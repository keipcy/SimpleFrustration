package frustration.game;

import frustration.board.BoardConfig;
import frustration.player.PlayerConfig;

import java.util.List;

public record GameConfig(
        BoardConfig boardConfig,
        List<PlayerConfig> players
) {}