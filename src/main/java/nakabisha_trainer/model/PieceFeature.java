package nakabisha_trainer.model;

public record PieceFeature(
        Side side,
        Integer index,
        boolean promoted) {

    public boolean isOnFile(int file) {
        if (file < 1 || file > 9) {
            throw new IllegalArgumentException(
                    "file must be between 1 and 9");
        }

        if (index == null) {
            return false;
        }

        return index % 9 == file - 1;
    }
}
