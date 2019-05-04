package com.backbase.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class KalahStateTest {

    @Test
    public void assertThatGameShouldStartNotFinished() {
        assertFalse(new Kalah().isGameFinished());
    }
}
