package board;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int boardLength;
    private final int tailLength;

    public Board(int boardLength, int tailLength) {
        this.boardLength = boardLength;
        this.tailLength = tailLength;
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
