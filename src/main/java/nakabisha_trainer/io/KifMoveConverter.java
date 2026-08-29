package nakabisha_trainer.io;

import nakabisha_trainer.model.Move;
import nakabisha_trainer.model.PieceType;
import nakabisha_trainer.model.Side;
import nakabisha_trainer.model.Square;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KifMoveConverter {

    private static final String PIECE = "歩|香|桂|銀|金|角|飛|玉";
    private static final String MODIFIER = "(?:左|右|直|引|寄|上)?";

    private static final Pattern NORMAL_MOVE_PATTERN = Pattern.compile(
            "^\\s*(\\d+)\\s+"
                    + "([1-9１-９])([1-9一二三四五六七八九])"
                    + "(" + PIECE + ")"
                    + MODIFIER
                    + "(成)?"
                    + "\\(([1-9])([1-9])\\)"
                    + ".*$"
    );

    private static final Pattern DROP_MOVE_PATTERN = Pattern.compile(
            "^\\s*(\\d+)\\s+"
                    + "([1-9１-９])([1-9一二三四五六七八九])"
                    + "(" + PIECE + ")打"
                    + ".*$"
    );

    private static final Pattern SAME_MOVE_PATTERN = Pattern.compile(
            "^\\s*(\\d+)\\s+"
                    + "同\\s*(" + PIECE + ")"
                    + MODIFIER
                    + "(成)?"
                    + "\\(([1-9])([1-9])\\)"
                    + ".*$"
    );

    private KifMoveConverter() {
    }

    public static Move convert(String line) {
        Matcher matcher = SAME_MOVE_PATTERN.matcher(line);
        if (matcher.matches()) {
            return convertSameMove(matcher);
        }

        matcher = DROP_MOVE_PATTERN.matcher(line);
        if (matcher.matches()) {
            return convertDropMove(matcher);
        }

        matcher = NORMAL_MOVE_PATTERN.matcher(line);
        if (matcher.matches()) {
            return convertNormalMove(matcher);
        }

        throw new IllegalArgumentException("Unsupported KIF move: " + line);
    }

    private static Move convertNormalMove(Matcher matcher) {
        Side side = parseSide(matcher.group(1));
        Square to = parseSquare(matcher.group(2), matcher.group(3));
        PieceType pieceType = parsePieceType(matcher.group(4));
        Square from = parseSquare(matcher.group(5), matcher.group(6));
        boolean promotion = matcher.group(7) != null;

        return new Move(side, from, to, pieceType, promotion);
    }

    private static Move convertDropMove(Matcher matcher) {
        Side side = parseSide(matcher.group(1));
        Square to = parseSquare(matcher.group(2), matcher.group(3));
        PieceType pieceType = parsePieceType(matcher.group(4));

        return new Move(side, null, to, pieceType, false);
    }

    private static Move convertSameMove(Matcher matcher) {
        Side side = parseSide(matcher.group(1));
        PieceType pieceType = parsePieceType(matcher.group(2));
        Square from = parseSquare(matcher.group(4), matcher.group(5));
        boolean promotion = matcher.group(3) != null;

        return new Move(side, from, null, pieceType, promotion);
    }

    private static Side parseSide(String moveNumber) {
        int number = Integer.parseInt(moveNumber);
        return number % 2 == 1 ? Side.SENTE : Side.GOTE;
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
