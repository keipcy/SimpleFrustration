package hitrule;

import player.Player;
import java.util.List;

public interface HitRule {
    boolean isMoveAllowed(Player mover, List<Player> others, String targetLabel);
}