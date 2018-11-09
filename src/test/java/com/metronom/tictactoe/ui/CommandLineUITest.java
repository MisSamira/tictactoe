package com.metronom.tictactoe.ui;

import com.metronom.tictactoe.config.Configuration;
import com.metronom.tictactoe.controller.Game;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;


public class CommandLineUITest extends TestCase {

    private CommandLineUI ui = CommandLineUI.getInstance();

    private Game game;

    @Before
    public void setUp() throws Exception {
        StringReader reader = new StringReader(
                "board_size=3\n" + "computer=C\n" + "player_1=O\n" + "player_2=X");
        StringReader inputStream = new StringReader("0,0\n0,1\n1,0\n1,1\n2,0\n2,1\n2,2\n");

        game = new Game(new Configuration(reader));
        ui.showGameStart(game,inputStream);
    }



    @Test(timeout = 1000)
    public void testStartGame() throws Exception {
        ui.startGame();
    }
}
