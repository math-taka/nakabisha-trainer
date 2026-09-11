package nakabisha_trainer.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionFeaturesTest {

    @Test
    void 正しい個数のPieceFeatureを保持できる() {
        List<PieceFeature> hisha = List.of(
                new PieceFeature(Side.SENTE, new Square(2, 8), false),
                new PieceFeature(Side.GOTE, new Square(8, 2), false)
        );
        List<PieceFeature> kaku = List.of(
                new PieceFeature(Side.SENTE, new Square(8, 8), false),
                new PieceFeature(Side.GOTE, new Square(2, 2), false)
        );
        List<PieceFeature> ou = List.of(
                new PieceFeature(Side.SENTE, new Square(5, 9), false),
                new PieceFeature(Side.GOTE, new Square(5, 1), false)
        );
        List<PieceFeature> gin = List.of(
                new PieceFeature(Side.SENTE, new Square(3, 9), false),
                new PieceFeature(Side.SENTE, new Square(7, 9), false),
                new PieceFeature(Side.GOTE, new Square(3, 1), false),
                new PieceFeature(Side.GOTE, new Square(7, 1), false)
        );

        PositionFeatures features = new PositionFeatures(hisha, kaku, ou, gin);

        assertEquals(hisha, features.hisha());
        assertEquals(kaku, features.kaku());
        assertEquals(ou, features.ou());
        assertEquals(gin, features.gin());
    }

    @ParameterizedTest
    @CsvSource({
            "hisha, 1",
            "hisha, 3",
            "kaku, 1",
            "kaku, 3",
            "ou, 1",
            "ou, 3",
            "gin, 3",
            "gin, 5"
    })
    void PieceFeatureの個数が不正なら例外になる(String pieceType, int count) {
        List<PieceFeature> hisha = pieceType.equals("hisha")
                ? createFeatures(count)
                : createFeatures(2);
        List<PieceFeature> kaku = pieceType.equals("kaku")
                ? createFeatures(count)
                : createFeatures(2);
        List<PieceFeature> ou = pieceType.equals("ou")
                ? createFeatures(count)
                : createFeatures(2);
        List<PieceFeature> gin = pieceType.equals("gin")
                ? createFeatures(count)
                : createFeatures(4);

        assertThrows(
                IllegalArgumentException.class,
                () -> new PositionFeatures(hisha, kaku, ou, gin)
        );
    }

    @Test
    void 渡したリストを変更してもPositionFeaturesは変わらない() {
        List<PieceFeature> hisha = new ArrayList<>(createFeatures(2));
        List<PieceFeature> kaku = new ArrayList<>(createFeatures(2));
        List<PieceFeature> ou = new ArrayList<>(createFeatures(2));
        List<PieceFeature> gin = new ArrayList<>(createFeatures(4));

        PositionFeatures features = new PositionFeatures(hisha, kaku, ou, gin);

        hisha.clear();
        kaku.clear();
        ou.clear();
        gin.clear();

        assertEquals(2, features.hisha().size());
        assertEquals(2, features.kaku().size());
        assertEquals(2, features.ou().size());
        assertEquals(4, features.gin().size());
    }

    private static List<PieceFeature> createFeatures(int count) {
        List<PieceFeature> features = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            features.add(new PieceFeature(Side.SENTE, null, false));
        }
        return features;
    }
}
