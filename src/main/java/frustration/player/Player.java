package frustration.player;

import java.util.List;

public class Player {
    public record MoveResult(
            String from,
            String to,
            boolean overshot,
            boolean won
    ) {}

    private final String colour;
    private final List<String> track;
    private int index;

    public Player(String colour, List<String> track) {
        this.colour = colour;
        this.track = track;
        this.index = 0;
    }

    public String getColour() {
        return colour;
    }

    public void applyMove(MoveResult move) {
        if (!move.overshot()) {
            this.index = track.indexOf(move.to());
        }
    }

    public String getPositionLabel() {
        if (index > track.size() - 1) return "FINISHED";
        return track.get(index);
    }

    public List<String> getTrack() {
        return track;
    }

    public int getPositionIndex() {
        return index;
    }
}
