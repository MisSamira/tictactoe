package com.metronom.tictactoe.ui;

import com.metronom.tictactoe.config.Configuration;
import com.metronom.tictactoe.controller.Game;
import com.metronom.tictactoe.domain.Move;
import com.metronom.tictactoe.domain.Player;
import com.metronom.tictactoe.exception.ErrorKey;
import com.metronom.tictactoe.exception.InvalidMoveException;

import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CommandLineUI implements UI {
    private static CommandLineUI instance = new CommandLineUI();

    private Game game;
    private Scanner scanner;


    private CommandLineUI() {
    }

    public static CommandLineUI getInstance() {
        return instance;
    }

    /**
     * initiate the game
     * print config of the game to console
     * print game help to console
     *
     * @param game
     * @param usersInput
     */
    @Override
    public void showGameStart(Game game, Reader usersInput) {
        this.game = game;
        this.scanner = new Scanner(usersInput);
        showConfigs();
        showHelp();
    }

    /**
     * Shows the running game configs to user
     */
    private void showConfigs() {
        Configuration config = game.getConfig();
        System.out.println(String.format(" === CONFIG : Board dimension %d, Player1Symbol %c, Player2Symbol %c, computerSymbol %c", config.getBoardConfig().getColumn(), config.getHumanPlayer1Config().getCharacter(), config.getHumanPlayer2Config().getCharacter(), config.getComputerConfig().getCharacter()));
    }

    /**
     * Shows board to user
     */
    @Override
    public void showBoard() {
        System.out.println("=======================");
        Character[][] boardTable = game.getBoard().getBoardTable();
        System.out.println(Stream.of(boardTable)
                .map(rowParts -> Stream.of(rowParts)
                        .map(Object::toString)
                        .collect(Collectors.joining("\t")))
                        .collect(Collectors.joining("\n")));
    }

    /**
     * Show the help for movement from the console
     */
    private void showHelp() {
        System.out.println("to move in your turn please give input as following format and then click enter: n,m");
        System.out.println("n is the row (begin from 0)");
        System.out.println("m is the column (begin from 0)");
    }

    /**
     * read the player's coordinate form console and return the move
     * if the coordinate is not in the board range or has invalid pattern
     * read until the player coordinate be acceptable
     * if the Line is empty @throw InvalidMoveException
     *
     * @param player
     * @return
     */
    private Move readMoveFromCLI(Player player) {
        Pattern pattern = Pattern.compile("^(\\d+),(\\d+)$");
        String line;

        while ((line = scanner.nextLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                int row = Integer.parseInt(matcher.group(1));
                int column = Integer.parseInt(matcher.group(2));
                return new Move(row, column, player.getSymbol());
            } else {
                System.out.println(ErrorKey.INVALID_INPUT_MOVE_CLI.getMessage());
            }
        }
        throw new InvalidMoveException(ErrorKey.PLEASE_SELECT_A_CELL.getMessage());
    }

    /**
     * every player on his turn make his move
     * if the move is acceptable, game goes on if not it will ask for the correct coordinate
     * the game will continue until someone win the game or the game become draw
     *
     * after each player's move the boardTable will print
     */
    @Override
    public void startGame() {
        Boolean gameOver = false;
        Boolean validMove;
        do {
            validMove = true;
            Player player = game.getNextPlayer();
            System.out.println(String.format("Player %c is on move", player.getCharacter()));
            while (validMove) {
                try {
                    Move move = player.makeMove().orElseGet(() -> readMoveFromCLI(player));
                    gameOver = game.playGame(move);
                    validMove = false;
                } catch (InvalidMoveException e){
                    System.out.println(e.getMessage());
                    validMove = true;
                }
            }
            showBoard();
        } while (!gameOver);
    }

}
