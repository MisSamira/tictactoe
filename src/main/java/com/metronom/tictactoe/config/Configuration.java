package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import com.metronom.tictactoe.exception.ErrorKey;

import java.io.Reader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public final class Configuration {

    public static final String PLAYER_1 = "player_1";
    public static final String PLAYER_2 = "player_2";
    public static final String COMPUTER = "computer";
    public static final String BOARD_SIZE = "board_size";
    private final Properties properties = new Properties();
    private BoardConfig boardConfig;
    private PlayerConfig humanPlayer1Config;
    private PlayerConfig humanPlayer2Config;
    private PlayerConfig computerConfig;

    /**
     * read config file and set configuration
     *
     * @param configFileStream
     * @throws BusinessException
     */
    public Configuration(final Reader configFileStream) throws BusinessException {
        try {
            properties.load(configFileStream);
        } catch (Exception e) {
            throw new BusinessException(ErrorKey.CONFIG_FILE_NOT_READABLE.getMessage());
        }

        setConfiguration();
    }

    /**
     * set all the configuration (players and board)
     */
    private void setConfiguration() {
        boardConfig = createBoardConfig();
        humanPlayer1Config = createPlayerConfig(PLAYER_1);
        humanPlayer2Config = createPlayerConfig(PLAYER_2);
        computerConfig = createPlayerConfig(COMPUTER);

        Set<Character> uniquePlayerCharacters = new HashSet<>();
        uniquePlayerCharacters.add(humanPlayer1Config.getCharacter());
        uniquePlayerCharacters.add(humanPlayer2Config.getCharacter());
        uniquePlayerCharacters.add(computerConfig.getCharacter());

        if (uniquePlayerCharacters.size() != 3) {
            throw new BusinessException(ErrorKey.PLAYER_CHARACTER_NOT_UNIQUE.getMessage());
        }
    }

    /**
     * create PlayerConfig
     *
     * @param propertyKey
     * @return PlayerConfig
     */
    private PlayerConfig createPlayerConfig(String propertyKey) {
        return new PlayerConfigImpl(properties.getProperty(propertyKey));
    }

    /**
     * create boardConfig
     *
     * @return BoardConfig
     * @throws BusinessException
     */
    private BoardConfig createBoardConfig() throws BusinessException {
        return new SquareBoardConfig(properties.getProperty(BOARD_SIZE));
    }

    public BoardConfig getBoardConfig() {
        return boardConfig;
    }

    public PlayerConfig getHumanPlayer1Config() {
        return humanPlayer1Config;
    }

    public PlayerConfig getHumanPlayer2Config() {
        return humanPlayer2Config;
    }

    public PlayerConfig getComputerConfig() {
        return computerConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration that = (Configuration) o;

        if (!properties.equals(that.properties)) return false;
        if (!boardConfig.equals(that.boardConfig)) return false;
        if (!humanPlayer1Config.equals(that.humanPlayer1Config)) return false;
        if (!humanPlayer2Config.equals(that.humanPlayer2Config)) return false;
        return computerConfig.equals(that.computerConfig);

    }

    @Override
    public int hashCode() {
        int result = properties.hashCode();
        result = 31 * result + boardConfig.hashCode();
        result = 31 * result + humanPlayer1Config.hashCode();
        result = 31 * result + humanPlayer2Config.hashCode();
        result = 31 * result + computerConfig.hashCode();
        return result;
    }
}
