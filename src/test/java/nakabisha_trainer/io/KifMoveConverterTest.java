package nakabisha_trainer.io;

import nakabisha_trainer.model.Move;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KifMoveConverterTest {

    @Test
    void convertsJapaneseNumeralsToSquare() {
        assertEquals(
                new Square(5, 3),
                KifMoveConverter.parseSquare("５", "三")
        );
    }

    @Test
    void convertsAsciiNumeralsToSquare() {
        assertEquals(
                new Square(5, 3),
                KifMoveConverter.parseSquare("5", "3")
        );
    }

    @Test
    void convertsMixedNumeralsToSquare() {
        assertEquals(
                new Square(5, 3),
                KifMoveConverter.parseSquare("５", "3")
        );

        assertEquals(
                new Square(5, 3),
                KifMoveConverter.parseSquare("5", "三")
        );
    }

    @Test
    void convertsAllNumeralsToCorrectNumbers() {
        assertEquals(1, KifMoveConverter.parseNumber("１"));
        assertEquals(2, KifMoveConverter.parseNumber("二"));
        assertEquals(3, KifMoveConverter.parseNumber("3"));
        assertEquals(4, KifMoveConverter.parseNumber("四"));
        assertEquals(5, KifMoveConverter.parseNumber("５"));
        assertEquals(6, KifMoveConverter.parseNumber("六"));
        assertEquals(7, KifMoveConverter.parseNumber("7"));
        assertEquals(8, KifMoveConverter.parseNumber("八"));
        assertEquals(9, KifMoveConverter.parseNumber("９"));
    }

    @Test
    void convertsFirstMove() {
        Move move = KifMoveConverter.convert(
                "   1 ５六歩(57)   ( 0:00/00:00:00)"
        );

        assertEquals(
                new Move(
                        new Square(5, 7),
                        new Square(5, 6),
                        PieceType.FU,
                        false
                ),
                move
        );
    }

    @Test
    void convertsSecondMove() {
        Move move = KifMoveConverter.convert(
                "   2 ３四歩(33)   ( 0:01/00:00:01)"
        );

        assertEquals(
                new Move(
                        new Square(3, 3),
                        new Square(3, 4),
                        PieceType.FU,
                        false
                ),
                move
        );
    }

    @Test
    void convertsThirdMove() {
        Move move = KifMoveConverter.convert(
                "   3 ５八飛(28)   ( 0:01/00:00:01)"
        );

        assertEquals(
                new Move(
                        new Square(2, 8),
                        new Square(5, 8),
                        PieceType.HI,
                        false
                ),
                move
        );
    }
}
