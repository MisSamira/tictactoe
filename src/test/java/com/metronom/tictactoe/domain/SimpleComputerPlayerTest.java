package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfigImpl;
import com.metronom.tictactoe.exception.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;


public class SimpleComputerPlayerTest {

    private SimpleComputerPlayer player;

    @Mock
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = Mockito.mock(Board.class);
        Mockito.when(board.getBoardSize()).thenReturn(3);
        player = new SimpleComputerPlayer(board,new PlayerConfigImpl("S"));
    }

    @Test
    public void testMakeMove() throws Exception {
        isEmptyTrue();
        Optional<Move> move = player.makeMove();

        assertEquals(0, move.get().getRow());
        assertEquals(0, move.get().getColumn());
        assertEquals(player.getCharacter(), move.get().getCharacter());
    }

    @Test(expected = InvalidMoveException.class)
    public void testMakeMoveFailed() {
        isEmptyFalse();
        player.makeMove();
    }

    private void isEmptyTrue(){
        Mockito.when(board.isEmptyAt(anyInt(),anyInt())).thenReturn(Boolean.TRUE);
    }
    private void isEmptyFalse(){
        Mockito.when(board.isEmptyAt(anyInt(),anyInt())).thenReturn(Boolean.FALSE);
    }
}
