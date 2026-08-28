package nakabisha_trainer.io;

import nakabisha_trainer.model.Square;

public class KifMoveConverter {

    private KifMoveConverter() {
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
}
