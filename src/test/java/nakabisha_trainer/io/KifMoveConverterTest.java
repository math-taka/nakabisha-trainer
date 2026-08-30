package nakabisha_trainer.io;

import nakabisha_trainer.model.Move;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Side;
import nakabisha_trainer.model.Square;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KifMoveConverterTest {

    @Test
    void convertsJapaneseNumeralsToSquare() {
        assertEquals(new Square(5, 3), KifMoveConverter.parseSquare("５", "三"));
    }

    @Test
    void convertsAsciiNumeralsToSquare() {
        assertEquals(new Square(5, 3), KifMoveConverter.parseSquare("5", "3"));
    }

    @Test
    void convertsMixedNumeralsToSquare() {
        assertEquals(new Square(5, 3), KifMoveConverter.parseSquare("５", "3"));
        assertEquals(new Square(5, 3), KifMoveConverter.parseSquare("5", "三"));
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
        Move move = KifMoveConverter.convert("   1 ５六歩(57)   ( 0:00/00:00:00)");
        assertEquals(new Move(Side.SENTE, new Square(5, 7), new Square(5, 6), PieceType.FU, false), move);
    }

    @Test
    void convertsSecondMove() {
        Move move = KifMoveConverter.convert("   2 ３四歩(33)   ( 0:01/00:00:01)");
        assertEquals(new Move(Side.GOTE, new Square(3, 3), new Square(3, 4), PieceType.FU, false), move);
    }

    @Test
    void convertsThirdMove() {
        Move move = KifMoveConverter.convert("   3 ５八飛(28)   ( 0:01/00:00:01)");
        assertEquals(new Move(Side.SENTE, new Square(2, 8), new Square(5, 8), PieceType.HI, false), move);
    }

    @Test
    void convertsPromotingMove() {
        Move move = KifMoveConverter.convert("   7 ２二角成(88)   ( 0:01/00:00:04)");
        assertEquals(new Move(Side.SENTE, new Square(8, 8), new Square(2, 2), PieceType.KAKU, true), move);
    }

    @Test
    void convertsDropMove() {
        Move move = KifMoveConverter.convert("   9 ６五角打   ( 0:01/00:00:05)");
        assertEquals(new Move(Side.SENTE, null, new Square(6, 5), PieceType.KAKU, false), move);
    }

    @Test
    void convertsSameMoveWithFullWidthSpace() {
        String line = "   8 同　銀(31)   ( 0:01/00:00:05)";
        System.out.println("line=[" + line + "]");
        line.codePoints()
                .forEach(c -> System.out.printf("U+%04X '%c'%n", c, c));

        System.out.println("[DEBUG] same prefix: "
                + Pattern.compile("^\\s*\\d+\\s+同").matcher(line).find());
        System.out.println("[DEBUG] same with whitespace: "
                + Pattern.compile("^\\s*\\d+\\s+同\\s*").matcher(line).find());
        System.out.println("[DEBUG] same with piece: "
                + Pattern.compile("^\\s*\\d+\\s+同\\s*(歩|香|桂|銀|金|角|飛|玉)").matcher(line).find());
        System.out.println("[DEBUG] same with opening parenthesis: "
                + Pattern.compile("^\\s*\\d+\\s+同\\s*(歩|香|桂|銀|金|角|飛|玉)\\(").matcher(line).find());

        Move move = KifMoveConverter.convert(line);
        assertEquals(new Move(Side.GOTE, new Square(3, 1), null, PieceType.GIN, false), move);
    }

    @Test
    void convertsSamePromotingMove() {
        Move move = KifMoveConverter.convert("  54 同　桂成(25)   ( 0:01/00:00:00)");
        assertEquals(new Move(Side.GOTE, new Square(2, 5), null, PieceType.KEI, true), move);
    }

    @Test
    void convertsMoveWithModifier() {
        Move move = KifMoveConverter.convert("  37 ５五銀左(56)   ( 0:01/00:00:00)");
        assertEquals(new Move(Side.SENTE, new Square(5, 6), new Square(5, 5), PieceType.GIN, false), move);
    }

    @Test
    void convertsPromotingMoveWithModifier() {
        Move move = KifMoveConverter.convert("  38 ５四銀引成(55)   ( 0:01/00:00:00)");
        assertEquals(new Move(Side.GOTE, new Square(5, 5), new Square(5, 4), PieceType.GIN, true), move);
    }

    @Test
    void convertsSameMoveWithModifier() {
        Move move = KifMoveConverter.convert("  81 同　歩左(67)   ( 0:01/00:00:00)");
        assertEquals(new Move(Side.SENTE, new Square(6, 7), null, PieceType.FU, false), move);
    }
}
