package nakabisha_trainer.model;

import java.util.Objects;

public final class Hand {

    private final int[] counts;

    public Hand() {
        counts = new int[PieceType.values().length];
    }

    public int count(PieceType type) {
        Objects.requireNonNull(type, "type must not be null");
        return counts[type.ordinal()];
    }
}
