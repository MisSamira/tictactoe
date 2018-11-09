package com.metronom.tictactoe.exception;

public enum ErrorKey {
    INVALID_COORDINATE("the given coordinate is invalid"),
    CELL_IS_FULL("the given cell is already full"),
    BOARD_IS_FULL("board is full"),
    BOARD_SIZE_INVALID("the given board size is invalid"),
    PLAYER_CHARACTER_INVALID("player character is invalid: "),
    PLAYER_CHARACTER_NOT_UNIQUE("Player characters are not unique"),
    CONFIG_FILE_NOT_READABLE("config file not readable"),
    FILE_NOT_FOUND("file not found"),
    INVALID_INPUT_MOVE_CLI("invalid input, input should be in following pattern: n,m"),
    MOVE_IS_NOT_IN_BOARD_RANGE("move is out of board's range"),
    PLEASE_SELECT_A_CELL("it's your turn please do a move")
    ;


    private final String message;

    ErrorKey(String value) {
        message = value;
    }

    public String getMessage() {
        return message;
    }
}
