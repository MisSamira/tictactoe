package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfigImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    HumanPlayer player;

    @Before
    public void setUp() throws Exception {
        player = new HumanPlayer(new PlayerConfigImpl("A"));
    }

    @Test
    public void testMakeMove() throws Exception {
        assertEquals(Optional.empty(),player.makeMove());
        assertEquals('A',player.getCharacter());
    }
}
