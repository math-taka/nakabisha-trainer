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
    void initialPositionHasStandardPiecePlacement() {
        Position position = Position.initial();

        assertPiece(position, 9, 1, Side.GOTE, PieceType.KYOSHA);
        assertPiece(position, 8, 1, Side.GOTE, PieceType.KEIMA);
        assertPiece(position, 7, 1, Side.GOTE, PieceType.GIN);
        assertPiece(position, 6, 1, Side.GOTE, PieceType.KIN);
        assertPiece(position, 5, 1, Side.GOTE, PieceType.OU);
        assertPiece(position, 4, 1, Side.GOTE, PieceType.KIN);
        assertPiece(position, 3, 1, Side.GOTE, PieceType.GIN);
        assertPiece(position, 2, 1, Side.GOTE, PieceType.KEIMA);
        assertPiece(position, 1, 1, Side.GOTE, PieceType.KYOSHA);
        assertPiece(position, 8, 2, Side.GOTE, PieceType.HISHA);
        assertPiece(position, 2, 2, Side.GOTE, PieceType.KAKU);

        for (int file = 1; file <= 9; file++) {
            assertPiece(position, file, 3, Side.GOTE, PieceType.FU);
            assertPiece(position, file, 7, Side.SENTE, PieceType.FU);
        }

        assertPiece(position, 8, 8, Side.SENTE, PieceType.KAKU);
        assertPiece(position, 2, 8, Side.SENTE, PieceType.HISHA);
        assertPiece(position, 9, 9, Side.SENTE, PieceType.KYOSHA);
        assertPiece(position, 8, 9, Side.SENTE, PieceType.KEIMA);
        assertPiece(position, 7, 9, Side.SENTE, PieceType.GIN);
        assertPiece(position, 6, 9, Side.SENTE, PieceType.KIN);
        assertPiece(position, 5, 9, Side.SENTE, PieceType.OU);
        assertPiece(position, 4, 9, Side.SENTE, PieceType.KIN);
        assertPiece(position, 3, 9, Side.SENTE, PieceType.GIN);
        assertPiece(position, 2, 9, Side.SENTE, PieceType.KEIMA);
        assertPiece(position, 1, 9, Side.SENTE, PieceType.KYOSHA);
    }

    @Test
    void initialPositionHasEmptyHandsAndSenteToMove() {
        Position position = Position.initial();

        for (PieceType type : PieceType.values()) {
            assertEquals(0, position.hand(Side.SENTE).count(type));
            assertEquals(0, position.hand(Side.GOTE).count(type));
        }
        assertEquals(Side.SENTE, position.sideToMove());
    }

    @Test
    void appliesDropMove() {
        Piece[] board = new Piece[81];
        Hand senteHand = new Hand();
        Hand goteHand = new Hand();
        senteHand.add(PieceType.HISHA);
        Position position = new Position(board, senteHand, goteHand, Side.SENTE);
        Square to = new Square(5, 5);
        Move move = new Move(null, to, PieceType.HISHA, false);

        Position next = position.apply(move);

        assertEquals(new Piece(Side.SENTE, PieceType.HISHA, false), next.pieceAt(to));
        assertEquals(0, next.hand(Side.SENTE).count(PieceType.HISHA));
        assertEquals(Side.GOTE, next.sideToMove());
    }

    @Test
    void applyingDropDoesNotModifyOriginalPosition() {
        Piece[] board = new Piece[81];
        Hand senteHand = new Hand();
        Hand goteHand = new Hand();
        senteHand.add(PieceType.HISHA);
        Position position = new Position(board, senteHand, goteHand, Side.SENTE);
        Square to = new Square(5, 5);
        Move move = new Move(null, to, PieceType.HISHA, false);

        Position next = position.apply(move);

        assertNull(position.pieceAt(to));
        assertEquals(1, position.hand(Side.SENTE).count(PieceType.HISHA));
        assertEquals(new Piece(Side.SENTE, PieceType.HISHA, false), next.pieceAt(to));
    }

    private static void assertPiece(
            Position position,
            int file,
            int rank,
            Side side,
            PieceType type) {
        Piece piece = position.pieceAt(new Square(file, rank));
        assertEquals(new Piece(side, type, false), piece);
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
