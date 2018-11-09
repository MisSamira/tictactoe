package com.metronom.tictactoe.exception;


public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String s) {
        super(s);
    }
}
