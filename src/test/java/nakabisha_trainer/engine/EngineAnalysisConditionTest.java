package nakabisha_trainer.engine;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EngineAnalysisConditionTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, Integer.MAX_VALUE})
    void 正の整数のdepthを指定できる(int depth) {
        EngineAnalysisCondition condition =
                new EngineAnalysisCondition(depth);

        assertEquals(depth, condition.depth());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void depthが正の整数でなければ例外になる(int depth) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EngineAnalysisCondition(depth)
        );
    }
}
