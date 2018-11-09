package com.metronom.tictactoe.ui;

import com.metronom.tictactoe.controller.Game;

import java.io.Reader;
import java.io.Serializable;

public interface UI extends Serializable {

    void showBoard();

    void showGameStart(Game game, Reader usersInput);

    void startGame();
}
