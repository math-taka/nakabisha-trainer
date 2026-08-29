package nakabisha_trainer.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

class KifReaderTest {

    private static final Path KIF_PATH =
            Path.of("test-data/kif/nakabisha_with_comment.kif");

    @Test
    void readsMoveLinesWithoutComments() throws IOException {
        List<String> moves = KifReader.read(KIF_PATH);

        assertFalse(moves.isEmpty());
        assertTrue(moves.stream().noneMatch(line -> line.startsWith("*")));
        assertTrue(moves.stream().noneMatch(line -> line.startsWith("まで")));
    }

    @Test
    void keepsMoveLinesUnchanged() throws IOException {
        List<String> moves = KifReader.read(KIF_PATH);

        assertEquals(
                "   1 ５六歩(57)   ( 0:00/00:00:00)",
                moves.get(0));
        assertEquals(
                "   2 ３四歩(33)   ( 0:01/00:00:01)",
                moves.get(1));
        assertEquals(
                "   3 ５八飛(28)   ( 0:01/00:00:01)",
                moves.get(2));
    }

    @Test
    void readsMovesAfterCommentLines() throws IOException {
        List<String> moves = KifReader.read(KIF_PATH);

        assertEquals(
                "  10 ４二玉(51)   ( 0:01/00:00:06)",
                moves.get(9));
        assertEquals(
                "  11 ３八玉(48)   ( 0:00/00:00:03)",
                moves.get(10));
    }

    @Test
    void readsSameMoveLines() throws IOException {
        List<String> moves = KifReader.read(KIF_PATH);

        assertTrue(
                moves.stream().anyMatch(line -> line.matches(
                        "^\\s*\\d+\\s+同\\s*.+$")));
    }
}
