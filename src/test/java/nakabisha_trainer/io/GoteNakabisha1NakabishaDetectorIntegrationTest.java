package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.Position;
import nakabisha_trainer.model.Side;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GoteNakabisha1NakabishaDetectorIntegrationTest {

    private static final Path KIF_PATH =
            Path.of("test-data/kif/gote_nakabisha1.kif");

    @Test
    void 後手中飛車の棋譜から20手目の局面が中飛車と判定される() throws Exception {
        GameRecord record = KifGameRecordReader.read(KIF_PATH);

        Position position = record.positionAt(20);

        assertTrue(
                NakabishaDetector.isNakabisha(
                        position.features(),
                        Side.GOTE));
    }
}
