package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.Move;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Side;
import nakabisha_trainer.model.Square;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KifGameRecordReaderTest {

    private static final Path KIF_PATH =
            Path.of("test-data/kif/sente_nakabisha.kif");

    @Test
    void readsAllMovesFromKif() throws Exception {
        GameRecord record = KifGameRecordReader.read(KIF_PATH);

        assertEquals(110, record.moves().size());
    }

    @Test
    void preservesMoveOrder() throws Exception {
        GameRecord record = KifGameRecordReader.read(KIF_PATH);
        List<Move> moves = record.moves();

        assertEquals(
                new Move(Side.SENTE, null, new Square(7, 7), PieceType.FU, false, false),
                moves.get(0));
        assertEquals(
                new Move(Side.GOTE, null, new Square(3, 3), PieceType.FU, false, false),
                moves.get(1));
        assertEquals(
                new Move(Side.SENTE, null, new Square(2, 7), PieceType.FU, false, false),
                moves.get(2));
    }
}
