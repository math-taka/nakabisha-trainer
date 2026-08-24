package nakabisha_trainer.model;

public record Square(int file, int rank) {

    public Square {
        if (file < 1 || file > 9) {
            throw new IllegalArgumentException("file must be between 1 and 9");
        }
        if (rank < 1 || rank > 9) {
            throw new IllegalArgumentException("rank must be between 1 and 9");
        }
    }

    public int index() {
        return (rank - 1) * 9 + (file - 1);
    }
}
