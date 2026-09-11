package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Position;
import nakabisha_trainer.model.Side;
import nakabisha_trainer.model.Square;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoteMukaibishaGameRecordPositionIntegrationTest {

    private static final Path KIF_PATH =
            Path.of("test-data/kif/gote_mukaibisha.kif");

    @Test
    void reproducesPositionAfterTwentyMoves() throws Exception {
        GameRecord record = KifGameRecordReader.read(KIF_PATH);

        Position position = record.positionAt(20);

        assertEquals(Side.SENTE, position.pieceAt(new Square(2, 8)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(2, 8)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(3, 8)).side());
        assertEquals(PieceType.KIN, position.pieceAt(new Square(3, 8)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(5, 8)).side());
        assertEquals(PieceType.OU, position.pieceAt(new Square(5, 8)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(7, 6)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(7, 6)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(7, 8)).side());
        assertEquals(PieceType.KIN, position.pieceAt(new Square(7, 8)).type());

        assertEquals(Side.GOTE, position.pieceAt(new Square(2, 2)).side());
        assertEquals(PieceType.GIN, position.pieceAt(new Square(2, 2)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(3, 4)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(3, 4)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(4, 2)).side());
        assertEquals(PieceType.KIN, position.pieceAt(new Square(4, 2)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(4, 4)).side());
        assertEquals(PieceType.KAKU, position.pieceAt(new Square(4, 4)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(5, 1)).side());
        assertEquals(PieceType.KIN, position.pieceAt(new Square(5, 1)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(6, 2)).side());
        assertEquals(PieceType.GIN, position.pieceAt(new Square(6, 2)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(7, 2)).side());
        assertEquals(PieceType.OU, position.pieceAt(new Square(7, 2)).type());

        assertEquals(1, position.hand(Side.SENTE).count(PieceType.HI));
        assertEquals(1, position.hand(Side.GOTE).count(PieceType.HI));
        assertEquals(1, position.hand(Side.GOTE).count(PieceType.FU));
        assertEquals(Side.SENTE, position.sideToMove());
    }
}
