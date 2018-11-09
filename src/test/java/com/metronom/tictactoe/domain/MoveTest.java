package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfigImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;


public class MoveTest {

    private Move move;

    @Before
    public void setUp() throws Exception {
        move = new Move(2,3,new PlayerConfigImpl("A"));
    }

    @Test
    public void testGetRow() throws Exception {
        assertEquals(2,move.getRow());
    }

    @Test
    public void testGetColumn() throws Exception {
        assertEquals(3,move.getColumn());
    }

    @Test
    public void testGetCharacter() throws Exception {
        assertEquals('A',move.getCharacter());
        assertNotSame('-', move.getCharacter());
    }
}
