package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfigImpl;
import com.metronom.tictactoe.exception.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private Board board = Board.getInstance();

    @Before
    public void setup(){
        board.createBoard(3);
    }

    @Test
    public void testCreateBoard() {
        assertEquals(3, board.getBoardSize());
    }

    @Test
    public void testSet() throws Exception {
        Move move = new Move(0,1,new PlayerConfigImpl("a"));
        board.set(move);

        assertEquals(Boolean.FALSE, board.isEmptyAt(0, 1));
        assertEquals(new Character('a'), board.getCharacterAt(0, 1));
        assertEquals(Boolean.FALSE, board.isWinner(move));
        assertEquals(Boolean.FALSE, board.isFull());
    }

    @Test(expected = InvalidMoveException.class)
    public void testSetInvalidMove() throws Exception {
        Move move = new Move(-1,1,new PlayerConfigImpl("a"));
        board.set(move);
    }

    @Test
    public void testIsFull() throws Exception {
        assertEquals(Boolean.FALSE, board.isFull());
    }

    @Test
    public void testGetBoardSize() throws Exception {
        assertEquals(3,board.getBoardSize());
    }

    @Test
    public void testGetBoardTable() throws Exception {
        assertEquals(3,board.getBoardTable().length);
        assertEquals(3, board.getBoardTable()[0].length);
        assertEquals(3,board.getBoardTable()[2].length);
    }

    @Test
    public void testGetCharacterAt() throws Exception {
        Move move = new Move(0,0,new PlayerConfigImpl("x"));
        board.set(move);

        assertEquals(Boolean.FALSE, board.isEmptyAt(0, 0));
        assertEquals(new Character('x'), board.getCharacterAt(0, 0));
    }

    @Test
    public void testIsEmptyAt() throws Exception {
        Move move = new Move(1,0,new PlayerConfigImpl("x"));
        board.set(move);

        assertEquals(Boolean.FALSE, board.isEmptyAt(1, 0));
        assertEquals(new Character('x'), board.getCharacterAt(1, 0));
    }

    @Test
    public void testIsWinner() throws Exception {
        Move move = new Move(2,0,new PlayerConfigImpl("x"));
        board.set(move);
        move = new Move(1,0,new PlayerConfigImpl("x"));
        board.set(move);
        move = new Move(0,0,new PlayerConfigImpl("x"));
        board.set(move);

        assertEquals(Boolean.FALSE, board.isEmptyAt(2, 0));
        assertEquals(new Character('x'), board.getCharacterAt(2, 0));
        assertEquals(Boolean.TRUE,board.isWinner(move));
    }
}
