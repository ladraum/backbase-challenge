package com.backbase;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
    public void assertThatStatusIsAMap() {
        assertThat(new Kalah().getStatus(), instanceOf(Map.class));
    }

    @Test
    public void assertThatInitialGameStateHasFourteenPits() {
        assertEquals(new Kalah().getStatus().size(), 14);
    }

    @Test
    public void assertThatInitialGameTheRightPits() {
        Map<Integer, Integer> status = new Kalah().getStatus();
        assertNotNull(status.get(1));
        assertNotNull(status.get(2));
        assertNotNull(status.get(3));
        assertNotNull(status.get(4));
        assertNotNull(status.get(5));
        assertNotNull(status.get(6));
        assertNotNull(status.get(7));
        assertNotNull(status.get(8));
        assertNotNull(status.get(9));
        assertNotNull(status.get(10));
        assertNotNull(status.get(11));
        assertNotNull(status.get(12));
        assertNotNull(status.get(13));
        assertNotNull(status.get(14));
    }

    @Test
    public void assertThatInitialGamePitsTheRightSeeds() {
        Map<Integer, Integer> status = new Kalah().getStatus();
        assertEquals(status.get(1), Integer.valueOf(6));
        assertEquals(status.get(2), Integer.valueOf(6));
        assertEquals(status.get(3), Integer.valueOf(6));
        assertEquals(status.get(4), Integer.valueOf(6));
        assertEquals(status.get(5), Integer.valueOf(6));
        assertEquals(status.get(6), Integer.valueOf(6));

        assertEquals(status.get(8), Integer.valueOf(6));
        assertEquals(status.get(9), Integer.valueOf(6));
        assertEquals(status.get(10), Integer.valueOf(6));
        assertEquals(status.get(11), Integer.valueOf(6));
        assertEquals(status.get(12), Integer.valueOf(6));
        assertEquals(status.get(13), Integer.valueOf(6));
    }
}
