package nakabisha_trainer.engine;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EngineEvaluationTest {

    @ParameterizedTest
    @CsvSource(
            value = {
                    "52, _",
                    "-120, _",
                    "0, _",
                    "_, 3",
                    "_, -3",
                    "52, 3"
            },
            nullValues = "_"
    )
    void 評価値を保持できる(
            Integer value,
            Integer mateDistance) {

        EngineEvaluation evaluation =
                new EngineEvaluation(value, mateDistance);

        assertEquals(value, evaluation.value());
        assertEquals(mateDistance, evaluation.mateDistance());
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "_, _"
            },
            nullValues = "_"
    )
    void valueとmateDistanceの両方がnullなら例外になる(
            Integer value,
            Integer mateDistance) {

        assertThrows(
                IllegalArgumentException.class,
                () -> new EngineEvaluation(value, mateDistance)
        );
    }
}
