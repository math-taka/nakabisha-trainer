package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Position;
import nakabisha_trainer.model.Side;
import nakabisha_trainer.model.Square;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GoteNakabisha1GameRecordPositionIntegrationTest {

    private static final Path KIF_PATH =
            Path.of("test-data/kif/gote_nakabisha1.kif");

    @Test
    void reproducesPositionAfterTwentyMoves() throws Exception {
        GameRecord record = KifGameRecordReader.read(KIF_PATH);

        Position position = record.positionAt(20);

        assertEquals(Side.SENTE, position.pieceAt(new Square(2, 5)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(2, 5)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(7, 6)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(7, 6)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(7, 7)).side());
        assertEquals(PieceType.GIN, position.pieceAt(new Square(7, 7)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(4, 8)).side());
        assertEquals(PieceType.GIN, position.pieceAt(new Square(4, 8)).type());
        assertEquals(Side.SENTE, position.pieceAt(new Square(8, 8)).side());
        assertEquals(PieceType.OU, position.pieceAt(new Square(8, 8)).type());

        assertEquals(Side.GOTE, position.pieceAt(new Square(3, 4)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(3, 4)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(5, 5)).side());
        assertEquals(PieceType.FU, position.pieceAt(new Square(5, 5)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(5, 2)).side());
        assertEquals(PieceType.HI, position.pieceAt(new Square(5, 2)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(3, 3)).side());
        assertEquals(PieceType.GIN, position.pieceAt(new Square(3, 3)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(8, 2)).side());
        assertEquals(PieceType.OU, position.pieceAt(new Square(8, 2)).type());
        assertEquals(Side.GOTE, position.pieceAt(new Square(7, 2)).side());
        assertEquals(PieceType.GIN, position.pieceAt(new Square(7, 2)).type());

        assertNull(position.pieceAt(new Square(2, 2)));
        assertNull(position.pieceAt(new Square(8, 8)));

        assertEquals(1, position.hand(Side.SENTE).count(PieceType.KAKU));
        assertEquals(1, position.hand(Side.GOTE).count(PieceType.KAKU));
        assertEquals(Side.SENTE, position.sideToMove());
    }
}
