package frustration.wincondition;

public interface WinCondition {
    boolean isOvershoot(int targetIndex, int trackSize);
    boolean isWin(int targetIndex, int lastIndex);
}