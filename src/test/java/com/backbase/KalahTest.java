package com.backbase;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

public class KalahTest {

    @Test
    public void assertThatTestsCanRun() {
        assertTrue(true);
    }

    @Test
    public void assertThatMainGameClassExists() {
        assertNotNull(new Kalah());
    }

    @Test
    public void assertThatGameHasAStatus() {
        assertNotNull(new Kalah().getStatus());
    }

    @Test
    public void assertThatStatusIsAList() {
        assertThat(new Kalah().getStatus(), instanceOf(List.class));
    }

    @Test
    public void assertThatInitialGameStateHasFourteenPits() {
        assertEquals(new Kalah().getStatus().size(), 14);
    }
}
