package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PlayerConfigImplTest {

    @Test
    public void testGetCharacter() throws Exception {
        Character character = 'o';
        PlayerConfig p = new PlayerConfigImpl(character.toString());
        assertEquals(character, p.getCharacter());
    }

    @Test(expected = BusinessException.class)
    public void testWithNullArgument() {
        new PlayerConfigImpl(null);
    }

    @Test(expected = BusinessException.class)
    public void testWithEmptyArgument() {
        new PlayerConfigImpl("");
    }

    @Test (expected = BusinessException.class)
    public void testWithMoreThanOneChar() {
        new PlayerConfigImpl("co");
    }

    @Test
    public void testValidInput() {
        new PlayerConfigImpl("c");
    }

    @Test(expected = BusinessException.class)
    public void testInValidCharacters() {
        new PlayerConfigImpl("-");
    }
}
