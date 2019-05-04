package com.backbase;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        assertThrows(IllegalKalahMoveException.class, () -> {
            new Kalah().playMove(7);
        });
    }

    @Test
    public void assertThatPlayerCantMoveFromHouseOnFourteen() throws Exception {
        assertThrows(IllegalKalahMoveException.class, () -> {
            new Kalah().playMove(14);
        });
    }

    @Test
    public void assertThatPlayerCanMoveASingleSeedToTheNextPit() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), game);
    }

    private void checkFinalState(List<Integer> finalStatus, Kalah kalah) {
        for (int i = 1; i <= 14; i++) {
            assertEquals(kalah.getStatus().get(i), finalStatus.get(i - 1));
        }
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

        checkFinalState(Arrays.asList(0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), game);
    }

    @Test
    public void assertThatPlayerCanMoveTwoSeedsToFollowingPits() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 2);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0), game);
    }

    @Test
    public void assertThatPlayerCanMoveThreeSeedsToPitsWithSeeds() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 3);
        status.put(5, 7);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 1, 8, 1, 0, 0, 0, 0, 0, 0, 0, 0), game);
    }
}
