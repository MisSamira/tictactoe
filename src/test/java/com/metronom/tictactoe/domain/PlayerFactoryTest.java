package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfigImpl;
import junit.framework.TestCase;
import org.junit.Test;


public class PlayerFactoryTest extends TestCase {

    @Test
    public void testBuildHumanPlayer() throws Exception {
        Player player = PlayerFactory.buildPlayer(Boolean.FALSE,new PlayerConfigImpl("D"));

        assertEquals('D',player.getCharacter());
        assertTrue(player instanceof HumanPlayer);
    }

    @Test
    public void testBuildComputerPlayer() throws Exception {
        Player player = PlayerFactory.buildPlayer(Boolean.TRUE,new PlayerConfigImpl("E"));

        assertEquals('E',player.getCharacter());
        assertTrue(player instanceof SimpleComputerPlayer);
    }
}
