package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    void createsPositionAndReturnsBoardPiece() {
        Piece[] board = new Piece[81];
        Square square = new Square(5, 8);
        Piece piece = new Piece(Side.SENTE, PieceType.HISHA, false);
        board[square.index()] = piece;
        Hand senteHand = new Hand();
        Hand goteHand = new Hand();

        Position position = new Position(board, senteHand, goteHand, Side.SENTE);

        assertEquals(piece, position.pieceAt(square));
        assertNull(position.pieceAt(new Square(1, 1)));
        assertEquals(Side.SENTE, position.sideToMove());
        assertEquals(senteHand, position.hand(Side.SENTE));
        assertEquals(goteHand, position.hand(Side.GOTE));
    }

    @Test
    void boardIsDefensivelyCopiedOnConstruction() {
        Piece[] board = new Piece[81];
        Square square = new Square(5, 8);
        Piece piece = new Piece(Side.SENTE, PieceType.HISHA, false);
        board[square.index()] = piece;

        Position position = new Position(
                board, new Hand(), new Hand(), Side.SENTE);

        board[square.index()] = null;

        assertEquals(piece, position.pieceAt(square));
    }

    @Test
    void boardReturnsDefensiveCopy() {
        Piece[] board = new Piece[81];
        Square square = new Square(5, 8);
        Piece piece = new Piece(Side.SENTE, PieceType.HISHA, false);
        board[square.index()] = piece;

        Position position = new Position(
                board, new Hand(), new Hand(), Side.SENTE);

        Piece[] returnedBoard = position.board();
        assertNotSame(returnedBoard, position.board());
        returnedBoard[square.index()] = null;

        assertEquals(piece, position.pieceAt(square));
    }

    @Test
    void rejectsInvalidBoardSize() {
        assertThrows(IllegalArgumentException.class,
                () -> new Position(new Piece[80], new Hand(), new Hand(), Side.SENTE));
        assertThrows(IllegalArgumentException.class,
                () -> new Position(new Piece[82], new Hand(), new Hand(), Side.SENTE));
    }

    @Test
    void rejectsNullArguments() {
        Piece[] board = new Piece[81];
        Hand hand = new Hand();

        assertThrows(NullPointerException.class,
                () -> new Position(null, hand, hand, Side.SENTE));
        assertThrows(NullPointerException.class,
                () -> new Position(board, null, hand, Side.SENTE));
        assertThrows(NullPointerException.class,
                () -> new Position(board, hand, null, Side.SENTE));
        assertThrows(NullPointerException.class,
                () -> new Position(board, hand, hand, null));
    }

    @Test
    void rejectsNullSquareAndSide() {
        Position position = new Position(
                new Piece[81], new Hand(), new Hand(), Side.SENTE);

        assertThrows(NullPointerException.class, () -> position.pieceAt(null));
        assertThrows(NullPointerException.class, () -> position.hand(null));
    }
}
