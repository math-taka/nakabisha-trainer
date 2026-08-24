package nakabisha_trainer.model;

import java.util.Objects;

public record Piece(Side side, PieceType type, boolean promoted) {
    public Piece {
        Objects.requireNonNull(side, "side must not be null");
        Objects.requireNonNull(type, "type must not be null");
    }
}
