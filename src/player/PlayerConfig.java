package player;

public record PlayerConfig(
        String colour,
        int startTile,
        String tailPrefix
) {}