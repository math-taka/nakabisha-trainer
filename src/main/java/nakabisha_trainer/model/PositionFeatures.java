package nakabisha_trainer.model;

import java.util.List;

public final class PositionFeatures {

    private final List<PieceFeature> hisha;
    private final List<PieceFeature> kaku;
    private final List<PieceFeature> ou;
    private final List<PieceFeature> gin;

    public PositionFeatures(
            List<PieceFeature> hisha,
            List<PieceFeature> kaku,
            List<PieceFeature> ou,
            List<PieceFeature> gin) {
        this.hisha = List.copyOf(hisha);
        this.kaku = List.copyOf(kaku);
        this.ou = List.copyOf(ou);
        this.gin = List.copyOf(gin);
    }

    public List<PieceFeature> hisha() {
        return hisha;
    }

    public List<PieceFeature> kaku() {
        return kaku;
    }

    public List<PieceFeature> ou() {
        return ou;
    }

    public List<PieceFeature> gin() {
        return gin;
    }
}
