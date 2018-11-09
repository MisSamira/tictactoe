package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfig;

public class Move {

    private final int row;
    private final int column;
    private PlayerConfig character;

    public Move(int row, int column, PlayerConfig character) {
        this.character = character;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getCharacter() {
        return character.getCharacter();
    }
}
