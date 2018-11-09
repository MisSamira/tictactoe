package com.metronom.tictactoe.controller;

import com.metronom.tictactoe.config.Configuration;
import com.metronom.tictactoe.domain.Move;
import com.metronom.tictactoe.domain.Player;
import com.metronom.tictactoe.exception.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class GameTest {

    private Game game;
    private Configuration configuration;

    @Before
    public void setUp(){
        StringReader reader = new StringReader(
                "board_size=3\n" + "computer=C\n" + "player_1=O\n" + "player_2=X");
        configuration = new Configuration(reader);
        game = new Game(configuration);
    }

    @Test
    public void testGetNextPlayer() throws Exception {
        Player player = game.getNextPlayer();
        assertNotNull(player);
    }

    @Test
    public void testGetConfig() throws Exception {
        assertNotNull(game.getConfig());
        assertEquals(configuration, game.getConfig());
        assertEquals(3, game.getConfig().getBoardConfig().getColumn());
    }

    @Test
    public void testGetBoard() throws Exception {
        assertNotNull(game.getBoard());
        assertEquals(3, game.getBoard().getBoardSize());
    }

    @Test
    public void testPlayGame() throws Exception {
        Move move = new Move(1,1,configuration.getHumanPlayer1Config());
        game.playGame(move);

        assertEquals('O',game.getBoard().getCharacterAt(1, 1).charValue());
    }

    @Test (expected = InvalidMoveException.class)
    public void testPlayGameInvalidMove() throws Exception {
        Move move = new Move(-1,1,configuration.getHumanPlayer1Config());
        game.playGame(move);
    }
}
