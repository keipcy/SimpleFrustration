package board;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int boardLength;
    private final int tailLength;

    public Board(int boardLength, int tailLength) {
        this.boardLength = boardLength;
        this.tailLength = tailLength;
    }

    // movement logic
    public Player.MoveResult calculateMove(Player player, int roll, boolean exactLanding) {
        int fromIndex = player.getPositionIndex();
        int targetIndex = fromIndex + roll;

        String fromLabel = player.getPositionLabel();

        // overshoot logic
        if (exactLanding && targetIndex >= player.getTrack().size()) {
            return new Player.MoveResult(
                    fromLabel,
                    fromLabel,
                    true,
                    false
            );
        }

        // clamp to end if overshoot allowed
        if (targetIndex >= player.getTrack().size()) {
            targetIndex = player.getTrack().size() - 1;
        }

        String toLabel = player.getTrack().get(targetIndex);
        boolean won = toLabel.contains("tail-" + tailLength);

        return new Player.MoveResult(
                fromLabel,
                toLabel,
                false,
                won
        );
    }

    public List<String> buildTrackForStart(int startTile) {
        List<String> track = new ArrayList<>();

        // main loop around the board
        for (int i = 0; i < boardLength; i++) {
            int pos = ((startTile - 1 + i) % boardLength) + 1;
            track.add(String.valueOf(pos));
        }

        // tail tiles
        for (int t = 1; t <= tailLength; t++) {
            track.add("tail-" + t);
        }

        return track;
    }
}
