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
        Move move = new Move(Side.SENTE, from, to, PieceType.HI, false);

        assertEquals(Side.SENTE, move.side());
        assertEquals(from, move.from());
        assertEquals(to, move.to());
        assertEquals(PieceType.HI, move.pieceType());
        assertFalse(move.promotion());
        assertFalse(move.isDrop());
        assertFalse(move.isSameDestination());
    }

    @Test
    void createsDropMove() {
        Square to = new Square(5, 5);
        Move move = new Move(Side.GOTE, null, to, PieceType.FU, false);

        assertEquals(Side.GOTE, move.side());
        assertNull(move.from());
        assertEquals(to, move.to());
        assertEquals(PieceType.FU, move.pieceType());
        assertFalse(move.promotion());
        assertTrue(move.isDrop());
        assertFalse(move.isSameDestination());
    }

    @Test
    void createsPromotionMove() {
        Move move = new Move(
                Side.SENTE,
                new Square(5, 7),
                new Square(5, 6),
                PieceType.HI,
                true);

        assertEquals(Side.SENTE, move.side());
        assertTrue(move.promotion());
        assertFalse(move.isDrop());
        assertFalse(move.isSameDestination());
    }

    @Test
    void createsSameDestinationMove() {
        Move move = new Move(
                Side.GOTE,
                new Square(5, 2),
                null,
                PieceType.GIN,
                false);

        assertEquals(Side.GOTE, move.side());
        assertEquals(new Square(5, 2), move.from());
        assertNull(move.to());
        assertEquals(PieceType.GIN, move.pieceType());
        assertFalse(move.promotion());
        assertFalse(move.isDrop());
        assertTrue(move.isSameDestination());
    }

    @Test
    void rejectsPromotionOnDrop() {
        assertThrows(IllegalArgumentException.class,
                () -> new Move(
                        Side.SENTE,
                        null,
                        new Square(5, 5),
                        PieceType.FU,
                        true));
    }

    @Test
    void rejectsNullSide() {
        assertThrows(NullPointerException.class,
                () -> new Move(
                        null,
                        new Square(5, 9),
                        new Square(5, 8),
                        PieceType.FU,
                        false));
    }

    @Test
    void rejectsNullPieceType() {
        assertThrows(NullPointerException.class,
                () -> new Move(
                        Side.SENTE,
                        new Square(5, 9),
                        new Square(5, 8),
                        null,
                        false));
    }
}
