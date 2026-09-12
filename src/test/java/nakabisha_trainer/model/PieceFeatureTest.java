package nakabisha_trainer.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceFeatureTest {

    @ParameterizedTest
    @CsvSource({
            "SENTE, 10, false",
            "GOTE, 70, false",
            "SENTE, 10, true",
            "GOTE, 70, true"
    })
    void PieceFeatureの値を保持できる(Side side, Integer index, boolean promoted) {
        PieceFeature feature = new PieceFeature(side, index, promoted);

        assertEquals(side, feature.side());
        assertEquals(index, feature.index());
        assertEquals(promoted, feature.promoted());
    }

    @ParameterizedTest
    @CsvSource({
            "SENTE, false",
            "GOTE, false",
            "SENTE, true",
            "GOTE, true"
    })
    void 盤上にない駒をnullで表現できる(Side side, boolean promoted) {
        PieceFeature feature = new PieceFeature(side, null, promoted);

        assertEquals(side, feature.side());
        assertEquals(null, feature.index());
        assertEquals(promoted, feature.promoted());
    }
}
