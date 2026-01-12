package frustration.wincondition;

public class OvershootAllowedWinCondition implements WinCondition {

    @Override
    public boolean isOvershoot(int targetIndex, int trackSize) {
        return false; // never invalid
    }

    @Override
    public boolean isWin(int targetIndex, int lastIndex) {
        return targetIndex >= lastIndex;
    }
}