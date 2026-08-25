package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    void newHandHasNoPieces() {
        Hand hand = new Hand();

        for (PieceType type : PieceType.values()) {
            assertEquals(0, hand.count(type));
        }
    }

    @Test
    void rejectsNullPieceType() {
        Hand hand = new Hand();

        assertThrows(NullPointerException.class, () -> hand.count(null));
    }
}
