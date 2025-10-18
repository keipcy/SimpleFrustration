package util;

public class MoveResult {
    private final boolean win;
    private final String message;

    public MoveResult(boolean win, String message) {
        this.win = win;
        this.message = message;
    }

    public boolean isWin() {
        return win;
    }

    public String getMessage() {
        return message;
    }
}
