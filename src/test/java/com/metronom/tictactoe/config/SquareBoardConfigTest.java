package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareBoardConfigTest {

    @Test(expected = BusinessException.class)
    public void testWithNullArgument() {
        new SquareBoardConfig(null);
    }

    @Test(expected = BusinessException.class)
    public void testWithEmptyArgument() {
        new SquareBoardConfig("");
    }

    @Test(expected = BusinessException.class)
    public void testInvalidBoardSizeLessThan3() {
        new SquareBoardConfig("2");
    }

    @Test(expected = BusinessException.class)
    public void testInvalidBoardSizeMoreThan10() {
        new SquareBoardConfig("11");
    }

    @Test
    public void testValidBoardSize() {
        new SquareBoardConfig("3");
    }

    @Test
    public void testGetRow() throws Exception {
        Integer size = 4;
        BoardConfig boardConfig = new SquareBoardConfig(size.toString());
        assertEquals(size.intValue(), boardConfig.getRow());
    }

    @Test
    public void testGetColumn() throws Exception {
        Integer size = 4;
        BoardConfig boardConfig = new SquareBoardConfig(size.toString());
        assertEquals(size.intValue(), boardConfig.getColumn());
    }
}
