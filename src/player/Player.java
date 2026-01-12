package player;

import java.util.List;

public class Player {
    public enum MoveResult { MOVED, WON, OVERSHOT }

    private final String colour;
    private final List<String> track;
    private int index; // position in track list

    public Player(String colour, List<String> track) {
        this.colour = colour;
        this.track = track;
        this.index = 0; // start at beginning of track
    }

    public String getColour() {
        return colour;
    }

    /**
     * Legacy move behavior (non-exact mode). Kept for compatibility.
     * Returns true if this move wins the game.
     */
    public boolean move(int roll) {
        return tryMove(roll, false) == MoveResult.WON;
    }

    /**
     * Attempt to move the player.
     * If exact is false: normal behavior (advance and win if landing on or passing final tile).
     * If exact is true: must land exactly on final tile to win; overshoot forfeits the turn and position is unchanged.
     * Returns a MoveResult indicating the outcome.
     */
    public MoveResult tryMove(int roll, boolean exact) {
        int lastIndex = track.size() - 1;
        if (!exact) {
            index += roll;
            if (index >= lastIndex) return MoveResult.WON;
            return MoveResult.MOVED;
        } else {
            int target = index + roll;
            if (target == lastIndex) {
                index = target;
                return MoveResult.WON;
            } else if (target > lastIndex) {
                // overshoot: forfeits turn, do not change index
                return MoveResult.OVERSHOT;
            } else {
                index = target;
                return MoveResult.MOVED;
            }
        }
    }

    public String getPositionLabel() {
        // show the last tile label when exactly on it; show FINISHED only when past it
        if (index > track.size() - 1) return "FINISHED";
        return track.get(index);
    }
}
