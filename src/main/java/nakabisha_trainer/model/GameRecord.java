package nakabisha_trainer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class GameRecord {

    private final List<Move> moves;

    public GameRecord() {
        this.moves = new ArrayList<>();
    }

    public void addMove(Move move) {
        moves.add(Objects.requireNonNull(move, "move must not be null"));
    }

    public List<Move> moves() {
        return List.copyOf(moves);
    }

    public Position positionAt(int ply) {
        if (ply < 0 || ply > moves.size()) {
            throw new IllegalArgumentException("ply is out of range: " + ply);
        }

        Position position = Position.initial();
        for (int i = 0; i < ply; i++) {
            position = position.apply(moves.get(i));
        }
        return position;
    }

    public Position currentPosition() {
        return positionAt(moves.size());
    }
}
