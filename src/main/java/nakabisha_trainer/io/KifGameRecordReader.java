package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.Move;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class KifGameRecordReader {

    private KifGameRecordReader() {
    }

    public static GameRecord read(Path path) throws IOException {
        List<String> lines = KifReader.read(path);
        GameRecord record = new GameRecord();

        for (String line : lines) {
            Move move = KifMoveConverter.convert(line);
            record.addMove(move);
        }

        return record;
    }
}
