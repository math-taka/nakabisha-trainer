package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.NakabishaDetector;
import nakabisha_trainer.model.Position;
import nakabisha_trainer.model.PositionFeatures;
import nakabisha_trainer.model.Side;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SenteNakabishaGameRecordPositionIntegrationTest {

    private static final Path KIF_PATH =
            Path.of("test-data/kif/sente_nakabisha.kif");

    @Test
    void 先手中飛車の局面を判定できる() throws Exception {
        GameRecord record = KifGameRecordReader.read(KIF_PATH);

        Position position = record.positionAt(20);
        PositionFeatures features = position.features();

        assertTrue(
                NakabishaDetector.isNakabisha(
                        features,
                        Side.SENTE));
    }
}
