package com.metronom.tictactoe.config;

import com.metronom.tictactoe.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizedPlayerConfigImplTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"X", true}, {"x", true}, {"z", true}, {".", false}, {",", false}, {"9", false}, {"@", false}
        });
    }

    private String input;

    private Boolean expected;

    public ParametrizedPlayerConfigImplTest(String input, Boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testCharacter() {
        Boolean isCreated;
        try {
            new PlayerConfigImpl(input);
            isCreated = true;
        } catch (BusinessException e){
            isCreated = false;
        }
        assertEquals(expected, isCreated);
    }
}
