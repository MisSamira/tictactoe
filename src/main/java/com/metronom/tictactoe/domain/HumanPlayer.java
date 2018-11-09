package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfig;

import java.util.Optional;

public final class HumanPlayer extends Player {

    public HumanPlayer(final PlayerConfig symbol) {
        this.setSymbol(symbol);
    }

    @Override
    public Optional<Move> makeMove() {
        return Optional.empty();
    }
}
