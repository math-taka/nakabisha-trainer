package nakabisha_trainer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SquareTest {
    @Test
    void createsValidSquare() {
        Square square = new Square(5, 8);

        assertEquals(5, square.file());
        assertEquals(8, square.rank());
    }

    @Test
    void calculatesIndex() {
        assertEquals(0, new Square(1, 1).index());
        assertEquals(8, new Square(9, 1).index());
        assertEquals(72, new Square(1, 9).index());
        assertEquals(80, new Square(9, 9).index());
        assertEquals(67, new Square(5, 8).index());
    }

    @Test
    void rejectsInvalidFile() {
        assertThrows(IllegalArgumentException.class, () -> new Square(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Square(10, 1));
    }

    @Test
    void rejectsInvalidRank() {
        assertThrows(IllegalArgumentException.class, () -> new Square(1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Square(1, 10));
    }
}
