package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PieceTypeTest {
    @Test
    void hasAllShogiPieceTypes() {
        assertEquals(8, PieceType.values().length);
        for (PieceType type : PieceType.values()) {
            assertNotNull(type);
        }
    }
}
