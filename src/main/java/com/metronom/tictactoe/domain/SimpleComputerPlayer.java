package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfig;
import com.metronom.tictactoe.exception.ErrorKey;
import com.metronom.tictactoe.exception.InvalidMoveException;

import java.util.Optional;

public class SimpleComputerPlayer extends Player {

    private final Board board;

    public SimpleComputerPlayer(Board board,PlayerConfig playerConfig) {
        this.board = board;
        setSymbol(playerConfig);
    }

    /**
     * this simpleComputerPlayer is a simple strategy to find the valuable cell and return it
     * for each empty cell the score calculates in 4 direction (horizontal,vertical,diagonal,rdiagonal)
     * and score of each cell is maximum value of its directions
     * and then the cell with highest score will select
     *
     * if the cell is empty value is 0
     * if the cell is filled and it fills with the computer's character value is +10
     * if the cell is filled and it fills with the enemies character value is -100
     *
     * the strategy is based on find the most valuable cell to win and not to avoid the enemies to win
     *
     * if all the cells are full throw InvalidMoveException
     *
     * @return move
     */
    @Override
    public Optional<Move> makeMove() {
        int boardSize = board.getBoardSize();
        Coordinate maxCoordinate = new Coordinate(-1,-1,-1000);
        int horizontal, vertical, diagonal, rdiagonal, tempMax;;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.isEmptyAt(i, j)) {
                    horizontal = 0;vertical = 0;diagonal = 0;rdiagonal = 0;
                    for (int x = 0; x < boardSize; x++) {
                        vertical = vertical + getScore(board.getCharacterAt(i, x));
                        horizontal = horizontal + getScore(board.getCharacterAt(x, j));
                        diagonal = (i == j) ? diagonal + getScore(board.getCharacterAt(x, x)) : diagonal;
                        rdiagonal = (i == boardSize - 1 - j) ? rdiagonal + getScore(board.getCharacterAt(x, boardSize - 1 - x)) : rdiagonal;
                    }

                    tempMax = Math.max(vertical, horizontal);
                    tempMax = (i == j) ? Math.max(tempMax, diagonal) : tempMax;
                    tempMax = (i == boardSize - 1 - j) ? Math.max(tempMax, rdiagonal) : tempMax;

                    if (tempMax > maxCoordinate.getScore()) {
                        maxCoordinate.setRow(i);
                        maxCoordinate.setColumn(j);
                        maxCoordinate.setScore(tempMax);
                    }
                    if (tempMax == (boardSize - 1) * 10) {
                        return Optional.of(new Move(maxCoordinate.getRow(), maxCoordinate.getColumn(), getSymbol()));
                    }
                }
            }
        }
        if(maxCoordinate.getScore() != -1000){
            return Optional.of(new Move(maxCoordinate.getRow(), maxCoordinate.getColumn(), getSymbol()));
        }
        throw new InvalidMoveException(ErrorKey.BOARD_IS_FULL.getMessage());
    }

    private int getScore(Character cellChar) {
        return cellChar == getCharacter() ? 10 : cellChar != board.getEmptyChar() ? - 100 : 0;
    }

    protected class Coordinate{
        private int row;
        private int column;
        private int score;

        public Coordinate(int row, int column, int score) {
            this.row = row;
            this.column = column;
            this.score = score;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "row=" + row +
                    ", column=" + column +
                    ", score=" + score +
                    '}';
        }
    }

}
