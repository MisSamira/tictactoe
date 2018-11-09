package com.metronom.tictactoe.exception;


public class BusinessException extends IllegalArgumentException {

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String s,String[] errorData) {
        super(s.concat(String.join(", ", errorData)));
    }
}
