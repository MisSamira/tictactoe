package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import com.metronom.tictactoe.exception.ErrorKey;

import java.util.Optional;

public final class SquareBoardConfig implements BoardConfig {

    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 10;
    private static final String PATTERN = "^[0-9]+$";
    private final int size;

    /**
     * check the pattern and size and create a squareBoardConfig
     *
     * @param size
     * @throws BusinessException
     */
    public SquareBoardConfig(final String size) throws BusinessException {
        Optional.ofNullable(size)
                .map(String::trim)
                .filter(str -> str.matches(PATTERN))
                .map(Integer::valueOf)
                .filter(val -> val >= MIN_SIZE)
                .filter(val -> val <= MAX_SIZE)
                .orElseThrow(() -> new BusinessException(ErrorKey.BOARD_SIZE_INVALID.getMessage()));
        this.size = Integer.parseInt(size);
    }

    @Override
    public int getRow() {
        return size;
    }

    @Override
    public int getColumn() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SquareBoardConfig that = (SquareBoardConfig) o;

        return size == that.size;

    }

    @Override
    public int hashCode() {
        return size;
    }
}
