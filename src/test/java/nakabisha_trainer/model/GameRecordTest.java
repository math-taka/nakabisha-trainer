package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class GameRecordTest {

    @Test
    void startsWithNoMoves() {
        GameRecord record = new GameRecord();
        assertEquals(List.of(), record.moves());
        assertEquals(Position.initial().board()[0], record.positionAt(0).board()[0]);
    }

    @Test
    void addsAndReturnsMoves() {
        GameRecord record = new GameRecord();
        Move move = new Move(Side.SENTE, new Square(7, 7), new Square(7, 6), PieceType.FU, false);
        record.addMove(move);
        assertEquals(List.of(move), record.moves());
    }

    @Test
    void returnedMovesCannotModifyRecord() {
        GameRecord record = new GameRecord();
        Move move = new Move(Side.SENTE, new Square(7, 7), new Square(7, 6), PieceType.FU, false);
        record.addMove(move);
        List<Move> moves = record.moves();
        assertThrows(UnsupportedOperationException.class, () -> moves.clear());
        assertEquals(List.of(move), record.moves());
    }

    @Test
    void rejectsNullMove() {
        GameRecord record = new GameRecord();
        assertThrows(NullPointerException.class, () -> record.addMove(null));
    }

    @Test
    void returnsPositionAtRequestedPly() {
        GameRecord record = new GameRecord();
        Move firstMove = new Move(Side.SENTE, new Square(7, 7), new Square(7, 6), PieceType.FU, false);
        Move secondMove = new Move(Side.GOTE, new Square(3, 3), new Square(3, 4), PieceType.FU, false);
        record.addMove(firstMove);
        record.addMove(secondMove);

        Position initial = record.positionAt(0);
        Position afterFirst = record.positionAt(1);
        Position afterSecond = record.positionAt(2);

        assertEquals(new Piece(Side.SENTE, PieceType.FU, false), initial.pieceAt(new Square(7, 7)));
        assertEquals(new Piece(Side.SENTE, PieceType.FU, false), afterFirst.pieceAt(new Square(7, 6)));
        assertEquals(new Piece(Side.GOTE, PieceType.FU, false), afterFirst.pieceAt(new Square(3, 3)));
        assertEquals(new Piece(Side.GOTE, PieceType.FU, false), afterSecond.pieceAt(new Square(3, 4)));
    }

    @Test
    void currentPositionIsPositionAfterAllMoves() {
        GameRecord record = new GameRecord();
        Move move = new Move(Side.SENTE, new Square(7, 7), new Square(7, 6), PieceType.FU, false);
        record.addMove(move);
        assertEquals(record.positionAt(1).board()[new Square(7, 6).index()], record.currentPosition().board()[new Square(7, 6).index()]);
        assertEquals(record.positionAt(1).sideToMove(), record.currentPosition().sideToMove());
    }

    @Test
    void rejectsPlyOutsideRange() {
        GameRecord record = new GameRecord();
        Move move = new Move(Side.SENTE, new Square(7, 7), new Square(7, 6), PieceType.FU, false);
        record.addMove(move);
        assertThrows(IllegalArgumentException.class, () -> record.positionAt(-1));
        assertThrows(IllegalArgumentException.class, () -> record.positionAt(2));
    }
}
