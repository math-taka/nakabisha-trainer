package nakabisha_trainer.model;

import java.util.Objects;

public record Move(
        Side side,
        Square from,
        Square to,
        PieceType pieceType,
        boolean promotion) {

    public Move {
        Objects.requireNonNull(side, "side must not be null");
        Objects.requireNonNull(pieceType, "pieceType must not be null");

        if (from == null && promotion) {
            throw new IllegalArgumentException(
                    "A drop move cannot be a promotion");
        }
    }

    public boolean isDrop() {
        return from == null;
    }

    public boolean isSameDestination() {
        return to == null;
    }
}
