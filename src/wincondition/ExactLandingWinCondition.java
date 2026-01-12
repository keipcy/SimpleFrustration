package wincondition;

public class ExactLandingWinCondition implements WinCondition {

    @Override
    public boolean isOvershoot(int targetIndex, int trackSize) {
        return targetIndex >= trackSize;
    }

    @Override
    public boolean isWin(int targetIndex, int lastIndex) {
        return targetIndex == lastIndex;
    }
}