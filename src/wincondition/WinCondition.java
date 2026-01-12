package wincondition;

public interface WinCondition {

    /**
     * @param targetIndex the index the player would move to
     * @param trackSize total size of the player's track
     * @return true if this move is an invalid overshoot
     */
    boolean isOvershoot(int targetIndex, int trackSize);

    /**
     * @param targetIndex the index the player would move to
     * @param lastIndex the final index of the track
     * @return true if this move wins the game
     */
    boolean isWin(int targetIndex, int lastIndex);
}