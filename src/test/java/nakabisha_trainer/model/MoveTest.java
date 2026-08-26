package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MoveTest {
    @Test
    void createsNormalMove() {
        Square from = new Square(5, 9);
        Square to = new Square(5, 8);
        Move move = new Move(from, to, PieceType.HI, false);

        assertEquals(from, move.from());
        assertEquals(to, move.to());
        assertEquals(PieceType.HI, move.pieceType());
        assertFalse(move.promotion());
        assertFalse(move.isDrop());
    }

    @Test
    void createsDropMove() {
        Square to = new Square(5, 5);
        Move move = new Move(null, to, PieceType.FU, false);

        assertNull(move.from());
        assertEquals(to, move.to());
        assertEquals(PieceType.FU, move.pieceType());
        assertFalse(move.promotion());
        assertTrue(move.isDrop());
    }

    @Test
    void createsPromotionMove() {
        Move move = new Move(
                new Square(5, 7),
                new Square(5, 6),
                PieceType.HI,
                true);

        assertTrue(move.promotion());
        assertFalse(move.isDrop());
    }

    @Test
    void rejectsPromotionOnDrop() {
        assertThrows(IllegalArgumentException.class,
                () -> new Move(null, new Square(5, 5), PieceType.FU, true));
    }

    @Test
    void rejectsNullTo() {
        assertThrows(NullPointerException.class,
                () -> new Move(new Square(5, 9), null, PieceType.FU, false));
    }

    @Test
    void rejectsNullPieceType() {
        assertThrows(NullPointerException.class,
                () -> new Move(new Square(5, 9), new Square(5, 8), null, false));
    }
}
