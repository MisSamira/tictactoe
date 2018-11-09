package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;


public class ConfigurationTest {

    private static final String CONFIG_STRING = "board_size=4\n" +
            "computer=C\n" +
            "player_1=X\n" +
            "player_2=O\n" ;

    private Configuration configuration;

    @Before
    public void setUp() throws IOException {
        Path path = Files.createTempFile("config", ".properties");
        File file = path.toFile();
        Files.write(path, CONFIG_STRING.getBytes(StandardCharsets.UTF_8));
        configuration = new Configuration(new FileReader(file));
    }

    @Test (expected = BusinessException.class)
    public void testNullArgument() {
        new Configuration(null);
    }

    @Test
    public void testGetBoardConfig() throws Exception {
        assertEquals(4, configuration.getBoardConfig().getRow());
        assertEquals(4, configuration.getBoardConfig().getColumn());
    }

    @Test
    public void testGetHumanPlayer1Config() throws Exception {
        assertEquals(new Character('X'), configuration.getHumanPlayer1Config().getCharacter());
    }

    @Test
    public void testGetHumanPlayer2Config() throws Exception {
        assertEquals(new Character('O'), configuration.getHumanPlayer2Config().getCharacter());

    }

    @Test
    public void testGetComputerConfig() throws Exception {
        assertEquals(new Character('C'), configuration.getComputerConfig().getCharacter());
    }
}
