package frustration.hitrule;

import frustration.player.Player;
import java.util.List;

public class IgnoreHitRule implements HitRule {

    @Override
    public boolean isMoveAllowed(Player mover, List<Player> others, String targetLabel) {
        return true;
    }
}