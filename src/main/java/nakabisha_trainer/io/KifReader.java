package nakabisha_trainer.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class KifReader {

    private static final Charset KIF_CHARSET = Charset.forName("Windows-31J");

    private static final Pattern MOVE_LINE_PATTERN =
            Pattern.compile("^\\s*\\d+\\s+\\S+");

    private static final String MOVE_HEADER =
            "手数----指手---------消費時間--";

    public static List<String> read(Path path) throws IOException {
        List<String> moves = new ArrayList<>();
        boolean readingMoves = false;

        List<String> lines = Files.readAllLines(path, KIF_CHARSET);

        for (String line : lines) {
            if (!readingMoves) {
                if (line.startsWith(MOVE_HEADER)) {
                    readingMoves = true;
                }
                continue;
            }

            if (isEndOfMoves(line)) {
                break;
            }

            if (isMoveLine(line)) {
                moves.add(line);
            }
        }

        return moves;
    }

    private static boolean isMoveLine(String line) {
        return MOVE_LINE_PATTERN.matcher(line).matches();
    }

    private static boolean isEndOfMoves(String line) {
        return line.startsWith("まで");
    }
}
