package frustration.wincondition;

public interface WinCondition {

    /**
     * @param targetIndex the index the main.java.player would move to
     * @param trackSize total size of the main.java.player's track
     * @return true if this move is an invalid overshoot
     */
    boolean isOvershoot(int targetIndex, int trackSize);

    /**
     * @param targetIndex the index the main.java.player would move to
     * @param lastIndex the final index of the track
     * @return true if this move wins the main.java.game
     */
    boolean isWin(int targetIndex, int lastIndex);
}