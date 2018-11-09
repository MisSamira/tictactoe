package com.metronom.tictactoe.domain;


import com.metronom.tictactoe.config.PlayerConfig;

import java.util.Optional;

public abstract class Player {

    private PlayerConfig symbol;

    public abstract Optional<Move> makeMove();

    public PlayerConfig getSymbol() {
        return symbol;
    }

    public void setSymbol(PlayerConfig symbol) {
        this.symbol = symbol;
    }

    public char getCharacter(){
        return symbol.getCharacter();
    }
}
