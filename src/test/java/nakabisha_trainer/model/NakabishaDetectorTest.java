package nakabisha_trainer.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NakabishaDetectorTest {

    @Test
    void 先手の飛車が5筋なら中飛車() {
        PositionFeatures features = createFeatures(
                new PieceFeature(Side.SENTE, 4, false),
                new PieceFeature(Side.GOTE, 10, false)
        );

        assertTrue(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.SENTE));
    }

    @Test
    void 後手の飛車が5筋なら中飛車() {
        PositionFeatures features = createFeatures(
                new PieceFeature(Side.SENTE, 10, false),
                new PieceFeature(Side.GOTE, 4, false)
        );

        assertTrue(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.GOTE));
    }

    @Test
    void 指定側の飛車が5筋でなければ中飛車ではない() {
        PositionFeatures features = createFeatures(
                new PieceFeature(Side.SENTE, 3, false),
                new PieceFeature(Side.GOTE, 10, false)
        );

        assertFalse(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.SENTE));
    }

    @Test
    void 相手の飛車だけが5筋でも中飛車ではない() {
        PositionFeatures features = createFeatures(
                new PieceFeature(Side.SENTE, 10, false),
                new PieceFeature(Side.GOTE, 4, false)
        );

        assertFalse(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.SENTE));
    }

    @Test
    void 飛車が持ち駒なら中飛車ではない() {
        PositionFeatures features = createFeatures(
                new PieceFeature(Side.SENTE, null, false),
                new PieceFeature(Side.GOTE, 10, false)
        );

        assertFalse(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.SENTE));
    }

    @Test
    void 成飛車が5筋なら中飛車() {
        PositionFeatures features = createFeatures(
                new PieceFeature(Side.SENTE, 4, true),
                new PieceFeature(Side.GOTE, 10, false)
        );

        assertTrue(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.SENTE));
    }

    private static PositionFeatures createFeatures(
            PieceFeature senteHisha,
            PieceFeature goteHisha) {

        List<PieceFeature> hisha = List.of(
                senteHisha,
                goteHisha
        );

        List<PieceFeature> kaku = List.of(
                new PieceFeature(Side.SENTE, 17, false),
                new PieceFeature(Side.GOTE, 9, false)
        );

        List<PieceFeature> ou = List.of(
                new PieceFeature(Side.SENTE, 76, false),
                new PieceFeature(Side.GOTE, 4, false)
        );

        List<PieceFeature> gin = List.of(
                new PieceFeature(Side.SENTE, 74, false),
                new PieceFeature(Side.SENTE, 80, false),
                new PieceFeature(Side.GOTE, 2, false),
                new PieceFeature(Side.GOTE, 8, false)
        );

        return new PositionFeatures(hisha, kaku, ou, gin);
    }
}
