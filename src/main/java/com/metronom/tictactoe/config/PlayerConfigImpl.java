package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import com.metronom.tictactoe.exception.ErrorKey;

import java.util.Optional;

public final class PlayerConfigImpl implements PlayerConfig {

    private static final String PATTERN = "[a-zA-Z]";

    private final Character character;

    /**
     * check the pattern and create a playerConfig
     *
     * @param playerCharacter
     * @throws BusinessException
     */
    public PlayerConfigImpl(final String playerCharacter) throws BusinessException {
        Optional.ofNullable(playerCharacter)
                .map(String::trim)
                .filter(val -> val.matches(PATTERN))
                .map(val -> val.charAt(0))
                .orElseThrow(() -> new BusinessException(ErrorKey.PLAYER_CHARACTER_INVALID.getMessage(), new String[]{playerCharacter}));

        this.character = playerCharacter.charAt(0);
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConfigImpl that = (PlayerConfigImpl) o;

        return character == that.character;
    }

    @Override
    public int hashCode() {
        return (int) character;
    }
}
