package nakabisha_trainer.model;

import java.util.List;

public record PositionFeatures(
        List<PieceFeature> hisha,
        List<PieceFeature> kaku,
        List<PieceFeature> ou,
        List<PieceFeature> gin) {

    public PositionFeatures {
        if (hisha.size() != 2) {
            throw new IllegalArgumentException("hisha must contain 2 pieces");
        }
        if (kaku.size() != 2) {
            throw new IllegalArgumentException("kaku must contain 2 pieces");
        }
        if (ou.size() != 2) {
            throw new IllegalArgumentException("ou must contain 2 pieces");
        }
        if (gin.size() != 4) {
            throw new IllegalArgumentException("gin must contain 4 pieces");
        }

        hisha = List.copyOf(hisha);
        kaku = List.copyOf(kaku);
        ou = List.copyOf(ou);
        gin = List.copyOf(gin);
    }
}
