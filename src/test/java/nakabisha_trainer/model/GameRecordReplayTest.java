package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;

class GameRecordReplayTest {

    @Test
    void replaysSenteNakabishaOpening() {
        GameRecord record = recordOf(List.of(
                move(Side.SENTE, 5, 7, 5, 6, PieceType.FU, false), move(Side.GOTE, 3, 3, 3, 4, PieceType.FU, false),
                move(Side.SENTE, 2, 8, 5, 8, PieceType.HI, false), move(Side.GOTE, 8, 2, 3, 2, PieceType.HI, false),
                move(Side.SENTE, 7, 7, 7, 6, PieceType.FU, false), move(Side.GOTE, 5, 1, 6, 2, PieceType.OU, false),
                move(Side.SENTE, 8, 8, 2, 2, PieceType.KAKU, true), move(Side.GOTE, 3, 1, 2, 2, PieceType.GIN, false),
                drop(Side.SENTE, 6, 5, PieceType.KAKU), drop(Side.GOTE, 5, 4, PieceType.KAKU),
                move(Side.SENTE, 6, 5, 5, 4, PieceType.KAKU, false), move(Side.GOTE, 5, 3, 5, 4, PieceType.FU, false),
                move(Side.SENTE, 5, 9, 4, 8, PieceType.OU, false), move(Side.GOTE, 4, 1, 5, 2, PieceType.KIN, false),
                move(Side.SENTE, 4, 8, 3, 8, PieceType.OU, false), move(Side.GOTE, 1, 3, 1, 4, PieceType.FU, false),
                move(Side.SENTE, 7, 9, 6, 8, PieceType.GIN, false), move(Side.GOTE, 6, 2, 7, 2, PieceType.OU, false),
                move(Side.SENTE, 6, 8, 5, 7, PieceType.GIN, false), move(Side.GOTE, 3, 4, 3, 5, PieceType.FU, false)));
        assertReplayCompleted(record);
        assertPiece(record.currentPosition(), 5, 7, Side.SENTE, PieceType.GIN, false);
        assertPiece(record.currentPosition(), 7, 2, Side.GOTE, PieceType.OU, false);
        assertEquals(1, record.currentPosition().hand(Side.SENTE).count(PieceType.KAKU));
        assertEquals(1, record.currentPosition().hand(Side.GOTE).count(PieceType.KAKU));
    }

    @Test
    void replaysGoteNakabishaOpening() {
        GameRecord record = recordOf(List.of(
                move(Side.SENTE, 2, 7, 2, 6, PieceType.FU, false), move(Side.GOTE, 3, 3, 3, 4, PieceType.FU, false),
                move(Side.SENTE, 7, 7, 7, 6, PieceType.FU, false), move(Side.GOTE, 5, 3, 5, 4, PieceType.FU, false),
                move(Side.SENTE, 2, 6, 2, 5, PieceType.FU, false), move(Side.GOTE, 8, 2, 5, 2, PieceType.HI, false),
                move(Side.SENTE, 8, 8, 2, 2, PieceType.KAKU, true), move(Side.GOTE, 3, 1, 2, 2, PieceType.GIN, false),
                move(Side.SENTE, 7, 9, 8, 8, PieceType.GIN, false), move(Side.GOTE, 2, 2, 3, 3, PieceType.GIN, false),
                move(Side.SENTE, 8, 8, 7, 7, PieceType.GIN, false), move(Side.GOTE, 5, 1, 6, 2, PieceType.OU, false),
                move(Side.SENTE, 3, 9, 4, 8, PieceType.GIN, false), move(Side.GOTE, 6, 2, 7, 2, PieceType.OU, false),
                move(Side.SENTE, 5, 9, 6, 8, PieceType.OU, false), move(Side.GOTE, 7, 2, 8, 2, PieceType.OU, false),
                move(Side.SENTE, 6, 8, 7, 8, PieceType.OU, false), move(Side.GOTE, 7, 1, 7, 2, PieceType.GIN, false),
                move(Side.SENTE, 7, 8, 8, 8, PieceType.OU, false), move(Side.GOTE, 5, 4, 5, 5, PieceType.FU, false)));
        assertReplayCompleted(record);
        assertPiece(record.currentPosition(), 8, 8, Side.SENTE, PieceType.OU, false);
        assertPiece(record.currentPosition(), 7, 2, Side.GOTE, PieceType.GIN, false);
        assertEquals(1, record.currentPosition().hand(Side.SENTE).count(PieceType.KAKU));
    }

    @Test
    void replaysSenteIbishaOpening() {
        GameRecord record = recordOf(List.of(
                move(Side.SENTE, 2, 7, 2, 6, PieceType.FU, false), move(Side.GOTE, 3, 3, 3, 4, PieceType.FU, false),
                move(Side.SENTE, 7, 7, 7, 6, PieceType.FU, false), move(Side.GOTE, 2, 2, 8, 8, PieceType.KAKU, true),
                move(Side.SENTE, 7, 9, 8, 8, PieceType.GIN, false), move(Side.GOTE, 3, 1, 4, 2, PieceType.GIN, false),
                move(Side.SENTE, 8, 8, 7, 7, PieceType.GIN, false), move(Side.GOTE, 4, 3, 4, 4, PieceType.FU, false),
                move(Side.SENTE, 6, 9, 7, 8, PieceType.KIN, false), move(Side.GOTE, 4, 2, 4, 3, PieceType.GIN, false),
                move(Side.SENTE, 3, 9, 3, 8, PieceType.GIN, false), move(Side.GOTE, 4, 1, 3, 2, PieceType.KIN, false),
                move(Side.SENTE, 4, 7, 4, 6, PieceType.FU, false), move(Side.GOTE, 8, 2, 4, 2, PieceType.HI, false),
                move(Side.SENTE, 3, 8, 4, 7, PieceType.GIN, false), move(Side.GOTE, 5, 1, 6, 2, PieceType.OU, false),
                move(Side.SENTE, 3, 7, 3, 6, PieceType.FU, false), move(Side.GOTE, 6, 2, 7, 2, PieceType.OU, false),
                move(Side.SENTE, 2, 9, 3, 7, PieceType.KEI, false), move(Side.GOTE, 2, 1, 3, 3, PieceType.KEI, false)));
        assertReplayCompleted(record);
        assertPiece(record.currentPosition(), 4, 2, Side.GOTE, PieceType.HI, false);
        assertPiece(record.currentPosition(), 3, 3, Side.GOTE, PieceType.KEI, false);
        assertPiece(record.currentPosition(), 7, 2, Side.GOTE, PieceType.OU, false);
    }

    @Test
    void replaysGoteMukaibishaOpening() {
        GameRecord record = recordOf(List.of(
                move(Side.SENTE, 2, 7, 2, 6, PieceType.FU, false), move(Side.GOTE, 3, 3, 3, 4, PieceType.FU, false),
                move(Side.SENTE, 2, 6, 2, 5, PieceType.FU, false), move(Side.GOTE, 2, 2, 4, 4, PieceType.KAKU, false),
                move(Side.SENTE, 2, 5, 2, 4, PieceType.FU, false), move(Side.GOTE, 2, 3, 2, 4, PieceType.FU, false),
                move(Side.SENTE, 2, 8, 2, 4, PieceType.HI, false), move(Side.GOTE, 8, 2, 2, 2, PieceType.HI, false),
                move(Side.SENTE, 2, 4, 2, 2, PieceType.HI, true), move(Side.GOTE, 3, 1, 2, 2, PieceType.GIN, false),
                drop(Side.SENTE, 2, 8, PieceType.FU), move(Side.GOTE, 5, 1, 6, 2, PieceType.OU, false),
                move(Side.SENTE, 4, 9, 3, 8, PieceType.KIN, false), move(Side.GOTE, 6, 2, 7, 2, PieceType.OU, false),
                move(Side.SENTE, 5, 9, 5, 8, PieceType.OU, false), move(Side.GOTE, 7, 1, 6, 2, PieceType.GIN, false),
                move(Side.SENTE, 6, 9, 7, 8, PieceType.KIN, false), move(Side.GOTE, 6, 1, 5, 1, PieceType.KIN, false),
                move(Side.SENTE, 7, 7, 7, 6, PieceType.FU, false), move(Side.GOTE, 4, 1, 4, 2, PieceType.KIN, false)));
        assertReplayCompleted(record);
        assertPiece(record.currentPosition(), 2, 2, Side.GOTE, PieceType.GIN, false);
        assertPiece(record.currentPosition(), 2, 8, Side.SENTE, PieceType.FU, false);
        assertPiece(record.currentPosition(), 7, 8, Side.SENTE, PieceType.KIN, false);
        assertEquals(0, record.currentPosition().hand(Side.SENTE).count(PieceType.FU));
        assertEquals(1, record.currentPosition().hand(Side.GOTE).count(PieceType.FU));
    }

    private static GameRecord recordOf(List<Move> moves) {
        GameRecord record = new GameRecord();
        moves.forEach(record::addMove);
        return record;
    }

    private static Move move(Side side, int fromFile, int fromRank, int toFile, int toRank, PieceType type, boolean promotion) {
        return new Move(side, new Square(fromFile, fromRank), new Square(toFile, toRank), type, promotion);
    }

    private static Move drop(Side side, int toFile, int toRank, PieceType type) {
        return new Move(side, null, new Square(toFile, toRank), type, false);
    }

    private static void assertReplayCompleted(GameRecord record) {
        assertEquals(20, record.moves().size());
        assertSame(Side.SENTE, record.currentPosition().sideToMove());
        assertArrayEquals(record.positionAt(20).board(), record.currentPosition().board());
        assertEquals(record.positionAt(20).sideToMove(), record.currentPosition().sideToMove());
    }

    private static void assertPiece(Position position, int file, int rank, Side side, PieceType type, boolean promoted) {
        assertEquals(new Piece(side, type, promoted), position.pieceAt(new Square(file, rank)));
    }
}
