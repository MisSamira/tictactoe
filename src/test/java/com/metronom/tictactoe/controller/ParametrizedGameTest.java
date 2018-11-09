package com.metronom.tictactoe.controller;


import com.metronom.tictactoe.config.Configuration;
import com.metronom.tictactoe.config.PlayerConfigImpl;
import com.metronom.tictactoe.domain.Move;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ParametrizedGameTest {

    private Move[] moves;

    private Game game;

    @Parameterized.Parameters
    public static Collection<Move[][]> data() {

        Move[][][] winningScenarios = {
                {{// win vertically
                        new Move(0, 0, new PlayerConfigImpl("C")),
                        new Move(0, 1, new PlayerConfigImpl("O")),
                        new Move(0, 2, new PlayerConfigImpl("X")),
                        new Move(1, 0, new PlayerConfigImpl("C")),
                        new Move(1, 1, new PlayerConfigImpl("O")),
                        new Move(1, 2, new PlayerConfigImpl("X")),
                        new Move(2, 0, new PlayerConfigImpl("C"))
                }},
                {{// win horizontally
                        new Move(0, 0, new PlayerConfigImpl("C")),
                        new Move(1, 0, new PlayerConfigImpl("O")),
                        new Move(2, 0, new PlayerConfigImpl("X")),
                        new Move(0, 1, new PlayerConfigImpl("C")),
                        new Move(1, 1, new PlayerConfigImpl("O")),
                        new Move(2, 1, new PlayerConfigImpl("X")),
                        new Move(0, 2, new PlayerConfigImpl("C"))
                }},
                {{// win diagonally
                        new Move(0, 0, new PlayerConfigImpl("C")),
                        new Move(0, 1, new PlayerConfigImpl("O")),
                        new Move(0, 2, new PlayerConfigImpl("X")),
                        new Move(1, 1, new PlayerConfigImpl("C")),
                        new Move(1, 0, new PlayerConfigImpl("O")),
                        new Move(1, 2, new PlayerConfigImpl("X")),
                        new Move(2, 2, new PlayerConfigImpl("C"))
                }},
                {{// win reverse-diagonally
                        new Move(0, 2, new PlayerConfigImpl("C")),
                        new Move(0, 1, new PlayerConfigImpl("O")),
                        new Move(0, 0, new PlayerConfigImpl("X")),
                        new Move(1, 1, new PlayerConfigImpl("C")),
                        new Move(1, 0, new PlayerConfigImpl("O")),
                        new Move(1, 2, new PlayerConfigImpl("X")),
                        new Move(2, 0, new PlayerConfigImpl("C"))
                }},
        };

        return Arrays.asList(winningScenarios);
    }


    public ParametrizedGameTest(Move[] moves) {
        this.moves = moves;
    }

    @Before
    public void setUp(){
        StringReader reader = new StringReader(
                "board_size=3\n" + "computer=C\n" + "player_1=O\n" + "player_2=X");
        game = new Game(new Configuration(reader));
    }

    @Test
    public void playGame() {
        Boolean gameOver = false;
        for(Move move : moves) {
            gameOver = game.playGame(move);
        }
        assertTrue(gameOver);
    }
}
