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

    public void add(PieceType type) {
        Objects.requireNonNull(type, "type must not be null");
        counts[type.ordinal()]++;
    }

    public void remove(PieceType type) {
        Objects.requireNonNull(type, "type must not be null");

        int index = type.ordinal();
        if (counts[index] == 0) {
            throw new IllegalStateException("No piece in hand: " + type);
        }

        counts[index]--;
    }
}
