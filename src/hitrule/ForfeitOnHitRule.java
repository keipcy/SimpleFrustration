package hitrule;

import player.Player;
import java.util.List;

public class ForfeitOnHitRule implements HitRule {

    @Override
    public boolean isMoveAllowed(Player mover, List<Player> others, String targetLabel) {
        return others.stream()
                .noneMatch(p -> p.getPositionLabel().equals(targetLabel));
    }
}