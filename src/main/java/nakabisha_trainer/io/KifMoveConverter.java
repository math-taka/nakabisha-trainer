package nakabisha_trainer.io;

import nakabisha_trainer.model.Move;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Square;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KifMoveConverter {

    private static final Pattern MOVE_PATTERN = Pattern.compile(
            "^\\s*(\\d+)\\s+"
                    + "([1-9１-９])([1-9一二三四五六七八九])"
                    + "(歩|香|桂|銀|金|角|飛|玉)"
                    + "\\((\\d)(\\d)\\)"
    );

    private KifMoveConverter() {
    }

    public static Move convert(String line) {
        Matcher matcher = MOVE_PATTERN.matcher(line);

        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "Unsupported KIF move: " + line);
        }

        Square to = parseSquare(matcher.group(2), matcher.group(3));
        PieceType pieceType = parsePieceType(matcher.group(4));
        Square from = parseSquare(matcher.group(5), matcher.group(6));

        return new Move(from, to, pieceType, false);
    }

    static Square parseSquare(String file, String rank) {
        return new Square(
                parseNumber(file),
                parseNumber(rank)
        );
    }

    static int parseNumber(String value) {
        return switch (value) {
            case "1", "１", "一" -> 1;
            case "2", "２", "二" -> 2;
            case "3", "３", "三" -> 3;
            case "4", "４", "四" -> 4;
            case "5", "５", "五" -> 5;
            case "6", "６", "六" -> 6;
            case "7", "７", "七" -> 7;
            case "8", "８", "八" -> 8;
            case "9", "９", "九" -> 9;
            default -> throw new IllegalArgumentException(
                    "Unsupported coordinate: " + value);
        };
    }

    static PieceType parsePieceType(String value) {
        return switch (value) {
            case "歩" -> PieceType.FU;
            case "香" -> PieceType.KYO;
            case "桂" -> PieceType.KEI;
            case "銀" -> PieceType.GIN;
            case "金" -> PieceType.KIN;
            case "角" -> PieceType.KAKU;
            case "飛" -> PieceType.HI;
            case "玉" -> PieceType.OU;
            default -> throw new IllegalArgumentException(
                    "Unsupported piece: " + value);
        };
    }
}
