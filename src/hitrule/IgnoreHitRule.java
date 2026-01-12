package hitrule;

import player.Player;
import java.util.List;

public class IgnoreHitRule implements HitRule {

    @Override
    public boolean isMoveAllowed(Player mover, List<Player> others, String targetLabel) {
        return true; // always allow the move
    }
}