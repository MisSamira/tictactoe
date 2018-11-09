package com.metronom.tictactoe.controller;

import com.metronom.tictactoe.config.Configuration;
import com.metronom.tictactoe.domain.Board;
import com.metronom.tictactoe.domain.Move;
import com.metronom.tictactoe.domain.Player;
import com.metronom.tictactoe.domain.PlayerFactory;

import java.util.Random;

public class Game {

    private Configuration config;
    private Board board = Board.getInstance();
    private Player[] players;
    private int turn;
    private Player currentPlayer;

    /**
     * initial domain entities based on given configuration
     * first turn is random
     *
     * @param config
     */
    public Game(final Configuration config) {
        this.config = config;
        board.createBoard(config.getBoardConfig().getRow());
        this.players = new Player[3];
        this.players[0] = PlayerFactory.buildPlayer(Boolean.TRUE, config.getComputerConfig());
        this.players[1] = PlayerFactory.buildPlayer(Boolean.FALSE, config.getHumanPlayer1Config());
        this.players[2] = PlayerFactory.buildPlayer(Boolean.FALSE, config.getHumanPlayer2Config());

        Random random = new Random();
        this.turn = random.nextInt(3);
        currentPlayer = players[turn];
    }

    public Player getNextPlayer() {
        currentPlayer = players[turn];
        return currentPlayer;
    }

    public Configuration getConfig() {
        return config;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * apply the move on the board and check if there is any winner/the game is finished or not
     *
     * @param move
     * @return true if the game is over otherwise return false
     */
    public Boolean playGame(final Move move) {
        board.set(move);
        turn = (turn == players.length - 1) ? 0 : turn + 1;

        if (board.isWinner(move)) {
            System.out.println(String.format("\n=== GAME OVER: Player %c win ===", currentPlayer.getCharacter()));
            return true;
        } else if (board.isFull()) {
            System.out.println("\n=== GAME OVER: Draw ===");
            return true;
        }
        return false;
    }
}
