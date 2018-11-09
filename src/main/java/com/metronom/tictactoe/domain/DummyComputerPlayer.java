package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfig;
import com.metronom.tictactoe.exception.ErrorKey;
import com.metronom.tictactoe.exception.InvalidMoveException;

import java.util.Optional;

public class DummyComputerPlayer extends Player {

    private final Board board;

    public DummyComputerPlayer(Board board, PlayerConfig playerConfig) {
        this.board = board;
        setSymbol(playerConfig);
    }

    /**
     * this simpleComputerPlayer find the first emptyPlace and make his move
     * if all the culls are full @throw InvalidMoveException
     *
     * @return
     */
    @Override
    public Optional<Move> makeMove() {
        int boardSize = board.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.isEmptyAt(i, j)) {
                    return Optional.of(new Move(i, j, getSymbol()));
                }
            }
        }
        throw new InvalidMoveException(ErrorKey.BOARD_IS_FULL.getMessage());
    }
}
