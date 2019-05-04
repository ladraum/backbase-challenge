package com.backbase;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class KalahInteractionTest {

    @Test
    public void assertThatGameStartNotFinished() {
        assertNotNull(new Kalah().isGameFinished());
    }

    @Test
    public void assertThatPlayerCanPlayItsTurn() throws Exception {
        assertThat(new Kalah().playMove(1), instanceOf(Kalah.class));
    }

    @Test
    public void assertThatPlayerCantMoveFromHouseOnSeven() throws Exception {
        assertThrows(IllegalKalahMoveException.class, () -> {new Kalah().playMove(7);});
    }

    @Test
    public void assertThatPlayerCantMoveFromHouseOnFourteen() throws Exception {
        assertThrows(IllegalKalahMoveException.class, () -> {new Kalah().playMove(14);});
    }

    @Test
    public void assertThatPlayerCanMoveASingleSeedToTheNextPit() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        assertEquals(status.get(1), Integer.valueOf(0));
        assertEquals(status.get(2), Integer.valueOf(0));
        assertEquals(status.get(3), Integer.valueOf(0));
        assertEquals(status.get(4), Integer.valueOf(1));
        assertEquals(status.get(5), Integer.valueOf(0));
        assertEquals(status.get(6), Integer.valueOf(0));
        assertEquals(status.get(7), Integer.valueOf(0));
        assertEquals(status.get(8), Integer.valueOf(0));
        assertEquals(status.get(9), Integer.valueOf(0));
        assertEquals(status.get(10), Integer.valueOf(0));
        assertEquals(status.get(11), Integer.valueOf(0));
        assertEquals(status.get(12), Integer.valueOf(0));
        assertEquals(status.get(13), Integer.valueOf(0));
        assertEquals(status.get(14), Integer.valueOf(0));
    }

    private Map<Integer, Integer> createClearStatus() {
        Map<Integer, Integer> status = new HashMap<>();
        for (int i = 1; i <= 14; i++) {
            status.put(i, 0);
        }
        return status;
    }

    @Test
    public void assertThatPlayerCanMoveASingleSeedToTheNextPitWithSeeds() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 1);
        status.put(4, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        assertEquals(status.get(1), Integer.valueOf(0));
        assertEquals(status.get(2), Integer.valueOf(0));
        assertEquals(status.get(3), Integer.valueOf(0));
        assertEquals(status.get(4), Integer.valueOf(2));
        assertEquals(status.get(5), Integer.valueOf(0));
        assertEquals(status.get(6), Integer.valueOf(0));
        assertEquals(status.get(7), Integer.valueOf(0));
        assertEquals(status.get(8), Integer.valueOf(0));
        assertEquals(status.get(9), Integer.valueOf(0));
        assertEquals(status.get(10), Integer.valueOf(0));
        assertEquals(status.get(11), Integer.valueOf(0));
        assertEquals(status.get(12), Integer.valueOf(0));
        assertEquals(status.get(13), Integer.valueOf(0));
        assertEquals(status.get(14), Integer.valueOf(0));
    }
}
