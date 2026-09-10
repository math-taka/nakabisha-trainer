package nakabisha_trainer.io;

import nakabisha_trainer.model.GameRecord;
import nakabisha_trainer.model.Move;
import nakabisha_trainer.model.Square;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class KifGameRecordReader {

    private KifGameRecordReader() {
    }

    public static GameRecord read(Path path) throws IOException {
        List<String> lines = KifReader.read(path);
        GameRecord record = new GameRecord();
        Square previousTo = null;

        for (String line : lines) {
            Move move = KifMoveConverter.convert(line);

            if (move.to() == null) {
                move = new Move(
                        move.side(),
                        move.from(),
                        previousTo,
                        move.pieceType(),
                        move.promoted(),
                        move.promotion()
                );
            }

            record.addMove(move);
            previousTo = move.to();
        }

        return record;
    }
}
