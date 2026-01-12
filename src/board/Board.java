package board;

import hitrule.HitRule;
import player.Player;
import wincondition.WinCondition;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final BoardConfig config;

    public Board(BoardConfig config) {
        this.config = config;
    }

    // movement logic
    public Player.MoveResult calculateMove(
            Player player,
            List<Player> others,
            int roll,
            WinCondition winCondition,
            HitRule hitRule
    ) {
        int fromIndex = player.getPositionIndex();
        int targetIndex = fromIndex + roll;

        String fromLabel = player.getPositionLabel();

        if (winCondition.isOvershoot(targetIndex, player.getTrack().size())) {
            return new Player.MoveResult(fromLabel, fromLabel, true, false);
        }

        int lastIndex = player.getTrack().size() - 1;
        int finalIndex = Math.min(targetIndex, lastIndex);

        String toLabel = player.getTrack().get(finalIndex);
        boolean won = winCondition.isWin(targetIndex, lastIndex);

        if (!hitRule.isMoveAllowed(player, others, toLabel)) {
            return new Player.MoveResult(fromLabel, fromLabel, false, false);
        }

        return new Player.MoveResult(fromLabel, toLabel, false, won);
    }

    public List<String> buildTrackForStart(int startTile, String tailPrefix) {
        List<String> track = new ArrayList<>();

        // main loop around the board
        for (int i = 0; i < config.boardLength(); i++) {
            int pos = ((startTile - 1 + i) % config.boardLength()) + 1;
            track.add(String.valueOf(pos));
        }

        // player-specific tail tiles
        for (int t = 1; t <= config.tailLength(); t++) {
            track.add(tailPrefix + t);
        }

        return track;
    }
}
