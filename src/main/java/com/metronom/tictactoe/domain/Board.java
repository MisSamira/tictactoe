package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.exception.ErrorKey;
import com.metronom.tictactoe.exception.InvalidMoveException;

import java.util.Arrays;


public class Board {
    public static final char EMPTY_CHAR = '-';
    private static Board instance = new Board();

    private Character[][] boardTable;
    private int freeCells;
    private int boardSize;

    private Board() {
    }

    public static Board getInstance() {
        return instance;
    }

    /**
     * create boardTable and freeCells based on given boardSize
     * @param boardSize
     */
    public void createBoard(int boardSize) {
        this.boardSize = boardSize;
        this.boardTable = new Character[boardSize][boardSize];
        this.freeCells = boardSize * boardSize;
        for (Character[] row : boardTable) {
            Arrays.fill(row, EMPTY_CHAR);
        }
    }

    /**
     * fill the coordinates with the char of the given move
     * if the move is not on the board or is full already @throw InvalidMoveException
     * @param move
     * @throws InvalidMoveException
     */
    public void set(Move move) throws InvalidMoveException {
        checkPositionIsAvailable(move.getRow(),move.getColumn());
        boardTable[move.getRow()][move.getColumn()] = move.getCharacter();
        freeCells--;
    }

    /**
     * if all the cells are full return true else return false
     * @return
     */
    public Boolean isFull() {
        return freeCells == 0;
    }

    /**
     * return the boardSize
     * @return
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * return the boardTable
     * @return
     */
    public Character[][] getBoardTable(){
        return boardTable;
    }

    public char getEmptyChar(){
        return EMPTY_CHAR;
    }

    /**
     * get the character of the coordinate
     * if the coordinate is not in the board @throw InvalidMoveException
     *
     * @param row
     * @param column
     * @return
     * @throws InvalidMoveException
     */
    public Character getCharacterAt(final int row, final int column) throws InvalidMoveException{
        if (checkValidMovement(row, column))
            return boardTable[row][column];
        throw new InvalidMoveException(ErrorKey.INVALID_COORDINATE.getMessage());
    }

    /**
     * return true of the coordinate is Empty otherwise return false
     * if the coordinate is not in the board @throw InvalidMoveException
     *
     * @param row
     * @param column
     * @return
     * @throws InvalidMoveException
     */
    public boolean isEmptyAt(final int row, final int column) throws InvalidMoveException {
        if (!checkValidMovement(row,column)) {
            throw new InvalidMoveException(ErrorKey.INVALID_COORDINATE.getMessage());
        }
        return boardTable[row][column].equals(EMPTY_CHAR);
    }

    /**
     * if the given move win the game return true otherwise return false
     * the algorithm is so simple
     * for each direction (horizontal,vertical,diagonal,rdiagonal) count all the move char
     * if the count of given char for one of directions equal to the board's size, it is the winner
     *
     * @param move
     * @return true if the move win the game otherwise return false
     */
    public Boolean isWinner(final Move move){
        int horizontal = 0,vertical = 0,diagonal = 0,rdiagonal = 0;

        int moveRow = move.getRow();
        int moveCol = move.getColumn();
        char moveChar = move.getCharacter();
        for(int i=0;i<boardSize ; i++){
            vertical = getCharacterAt(moveRow, i) == moveChar ? vertical+1 : vertical;
            horizontal = getCharacterAt(i,moveCol) == moveChar ? horizontal+1 : horizontal;
            diagonal = getCharacterAt(i,i) == moveChar ? diagonal+1 : diagonal;
            rdiagonal = getCharacterAt(i,boardSize-1-i) == moveChar ? rdiagonal+1 : rdiagonal;
        }
        return horizontal == boardSize || vertical == boardSize || diagonal == boardSize || rdiagonal == boardSize;
    }

    /**
     * check if the move is in the boardRange or not
     *
     * @param row
     * @param column
     * @return true if it is in the range otherwise return false
     */
    private boolean checkValidMovement(int row, int column) {
        return row >= 0 && column >= 0 && row < boardSize && column < boardSize;
    }

    /**
     * check the move is in the boardRange and it is and Empty Cell
     *
     * @param row
     * @param column
     */
    private void checkPositionIsAvailable(final int row, final int column){
        if (!checkValidMovement(row, column))
            throw new InvalidMoveException(ErrorKey.INVALID_COORDINATE.getMessage());

        if (!boardTable[row][column].equals(EMPTY_CHAR))
            throw new InvalidMoveException(ErrorKey.CELL_IS_FULL.getMessage());
    }

}
