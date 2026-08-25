package nakabisha_trainer.model;

public enum Side {
    SENTE,
    GOTE;

    public Side opposite() {
        return this == SENTE ? GOTE : SENTE;
    }
}
