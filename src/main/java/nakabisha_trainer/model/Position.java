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
