package com.metronom.tictactoe;

import com.metronom.tictactoe.config.Configuration;
import com.metronom.tictactoe.controller.Game;
import com.metronom.tictactoe.exception.BusinessException;
import com.metronom.tictactoe.exception.ErrorKey;
import com.metronom.tictactoe.exception.ParseException;
import com.metronom.tictactoe.ui.CommandLineUI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Optional;


public final class TicTacToe {

    /**
     * create configuration
     * create a Game
     * ui start the game
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Configuration configuration = getArgsAndCreateConfiguration(args);
            CommandLineUI ui = CommandLineUI.getInstance();
            Game game = new Game(configuration);
            ui.showGameStart(game, new InputStreamReader(System.in));
            ui.startGame();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * get the file path from the console
     * and read it as a file
     * and create configuration
     *
     *
     * @param args
     * @return
     * @throws ParseException
     * @throws BusinessException
     *
     */
    private static Configuration getArgsAndCreateConfiguration(String[] args) throws ParseException, BusinessException {
        Optional.ofNullable(args)
                .filter(val -> args.length >= 0)
                .filter(val -> args.length <= 1)
                .orElseThrow(() -> new ParseException(ErrorKey.BOARD_SIZE_INVALID.getMessage()));

        FileReader configFileStream;
        try {
            configFileStream = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            throw new ParseException(ErrorKey.FILE_NOT_FOUND.getMessage());
        }
        return new Configuration(configFileStream);
    }

}
