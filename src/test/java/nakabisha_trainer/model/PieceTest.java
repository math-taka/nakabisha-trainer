package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    void createsUnpromotedPiece() {
        Piece piece = new Piece(Side.SENTE, PieceType.HISHA, false);

        assertEquals(Side.SENTE, piece.side());
        assertEquals(PieceType.HISHA, piece.type());
        assertFalse(piece.promoted());
    }

    @Test
    void rejectsNullSide() {
        assertThrows(NullPointerException.class,
                () -> new Piece(null, PieceType.FU, false));
    }

    @Test
    void rejectsNullPieceType() {
        assertThrows(NullPointerException.class,
                () -> new Piece(Side.GOTE, null, false));
    }

    @Test
    void createsPromotedPiece() {
        Piece piece = new Piece(Side.GOTE, PieceType.GIN, true);

        assertEquals(Side.GOTE, piece.side());
        assertEquals(PieceType.GIN, piece.type());
        assertEquals(true, piece.promoted());
    }
}
