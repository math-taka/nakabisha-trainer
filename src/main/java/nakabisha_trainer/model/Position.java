package nakabisha_trainer.model;

import java.util.Arrays;
import java.util.Objects;

public final class Position {

    private final Piece[] board;
    private final Hand senteHand;
    private final Hand goteHand;
    private final Side sideToMove;

    public Position(
            Piece[] board,
            Hand senteHand,
            Hand goteHand,
            Side sideToMove) {

        Objects.requireNonNull(board, "board must not be null");
        Objects.requireNonNull(senteHand, "senteHand must not be null");
        Objects.requireNonNull(goteHand, "goteHand must not be null");
        Objects.requireNonNull(sideToMove, "sideToMove must not be null");

        if (board.length != 81) {
            throw new IllegalArgumentException(
                    "board must contain 81 squares");
        }

        this.board = Arrays.copyOf(board, board.length);
        this.senteHand = senteHand;
        this.goteHand = goteHand;
        this.sideToMove = sideToMove;
    }

    public static Position initial() {
        Piece[] board = new Piece[81];

        // Gote's back rank.
        board[new Square(9, 1).index()] = new Piece(Side.GOTE, PieceType.KYOSHA, false);
        board[new Square(8, 1).index()] = new Piece(Side.GOTE, PieceType.KEIMA, false);
        board[new Square(7, 1).index()] = new Piece(Side.GOTE, PieceType.GIN, false);
        board[new Square(6, 1).index()] = new Piece(Side.GOTE, PieceType.KIN, false);
        board[new Square(5, 1).index()] = new Piece(Side.GOTE, PieceType.OU, false);
        board[new Square(4, 1).index()] = new Piece(Side.GOTE, PieceType.KIN, false);
        board[new Square(3, 1).index()] = new Piece(Side.GOTE, PieceType.GIN, false);
        board[new Square(2, 1).index()] = new Piece(Side.GOTE, PieceType.KEIMA, false);
        board[new Square(1, 1).index()] = new Piece(Side.GOTE, PieceType.KYOSHA, false);

        // Gote's bishop and rook.
        board[new Square(8, 2).index()] = new Piece(Side.GOTE, PieceType.HISHA, false);
        board[new Square(2, 2).index()] = new Piece(Side.GOTE, PieceType.KAKU, false);

        // Gote's pawns.
        for (int file = 1; file <= 9; file++) {
            board[new Square(file, 3).index()] =
                    new Piece(Side.GOTE, PieceType.FU, false);
        }

        // Sente's pawns.
        for (int file = 1; file <= 9; file++) {
            board[new Square(file, 7).index()] =
                    new Piece(Side.SENTE, PieceType.FU, false);
        }

        // Sente's bishop and rook.
        board[new Square(8, 8).index()] = new Piece(Side.SENTE, PieceType.KAKU, false);
        board[new Square(2, 8).index()] = new Piece(Side.SENTE, PieceType.HISHA, false);

        // Sente's back rank.
        board[new Square(9, 9).index()] = new Piece(Side.SENTE, PieceType.KYOSHA, false);
        board[new Square(8, 9).index()] = new Piece(Side.SENTE, PieceType.KEIMA, false);
        board[new Square(7, 9).index()] = new Piece(Side.SENTE, PieceType.GIN, false);
        board[new Square(6, 9).index()] = new Piece(Side.SENTE, PieceType.KIN, false);
        board[new Square(5, 9).index()] = new Piece(Side.SENTE, PieceType.OU, false);
        board[new Square(4, 9).index()] = new Piece(Side.SENTE, PieceType.KIN, false);
        board[new Square(3, 9).index()] = new Piece(Side.SENTE, PieceType.GIN, false);
        board[new Square(2, 9).index()] = new Piece(Side.SENTE, PieceType.KEIMA, false);
        board[new Square(1, 9).index()] = new Piece(Side.SENTE, PieceType.KYOSHA, false);

        return new Position(board, new Hand(), new Hand(), Side.SENTE);
    }

    public Piece pieceAt(Square square) {
        Objects.requireNonNull(square, "square must not be null");
        return board[square.index()];
    }

    public Hand hand(Side side) {
        Objects.requireNonNull(side, "side must not be null");

        return side == Side.SENTE
                ? senteHand
                : goteHand;
    }

    public Side sideToMove() {
        return sideToMove;
    }

    public Piece[] board() {
        return Arrays.copyOf(board, board.length);
    }
}
