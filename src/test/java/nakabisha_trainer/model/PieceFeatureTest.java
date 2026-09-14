package nakabisha_trainer.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @ParameterizedTest
    @CsvSource({
            "0, 1, true",
            "4, 1, false",
            "4, 5, true",
            "13, 5, true",
            "14, 5, false",
            "80, 9, true"
    })
    void 指定した筋に駒がいるか判定できる(
            Integer index,
            int file,
            boolean expected) {

        PieceFeature feature =
                new PieceFeature(Side.SENTE, index, false);

        assertEquals(expected, feature.isOnFile(file));
    }

    @ParameterizedTest
    @CsvSource({
            "SENTE, false",
            "GOTE, false",
            "SENTE, true",
            "GOTE, true"
    })
    void 持ち駒はどの筋にもいない(Side side, boolean promoted) {
        PieceFeature feature =
                new PieceFeature(side, null, promoted);

        assertEquals(false, feature.isOnFile(1));
        assertEquals(false, feature.isOnFile(5));
        assertEquals(false, feature.isOnFile(9));
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "0",
            "10",
            "11"
    })
    void 筋が1から9の範囲外なら例外になる(int file) {
        PieceFeature feature =
                new PieceFeature(Side.SENTE, 4, false);

        assertThrows(
                IllegalArgumentException.class,
                () -> feature.isOnFile(file)
        );
    }
}
