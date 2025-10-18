package board;

import player.Player;
import util.MoveResult;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int mainTrackLength;
    private final int tailLength;
    private final Map<Player, Integer> playerPositions;

    public Board(int mainTrackLength, int tailLength) {
        this.mainTrackLength = mainTrackLength;
        this.tailLength = tailLength;
        this.playerPositions = new HashMap<>();
    }

    public MoveResult movePlayer(Player player, int spaces) {
        int current = playerPositions.getOrDefault(player, 1);
        int newPosition = current + spaces;

        int finish = mainTrackLength + tailLength;

        if(newPosition == finish || newPosition > finish) {
            playerPositions.put(player, newPosition);
            return new MoveResult(true, "Landed at the finish line!");
        } else {
            playerPositions.put(player, newPosition);
            return new MoveResult(false, "Moved to position " + newPosition);

        }
    }

    public void addPlayer(Player player, int homePosition) {
        playerPositions.put(player, homePosition);
    }
}
