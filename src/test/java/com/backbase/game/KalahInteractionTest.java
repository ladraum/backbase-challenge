package com.backbase.game;

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

    private void checkFinalState(List<Integer> finalStatus, Kalah kalah) {
        assertEquals(kalah.getStatus().get(1), finalStatus.get(0));
        assertEquals(kalah.getStatus().get(2), finalStatus.get(1));
        assertEquals(kalah.getStatus().get(3), finalStatus.get(2));
        assertEquals(kalah.getStatus().get(4), finalStatus.get(3));
        assertEquals(kalah.getStatus().get(5), finalStatus.get(4));
        assertEquals(kalah.getStatus().get(6), finalStatus.get(5));
        assertEquals(kalah.getStatus().get(7), finalStatus.get(6));
        assertEquals(kalah.getStatus().get(8), finalStatus.get(7));
        assertEquals(kalah.getStatus().get(9), finalStatus.get(8));
        assertEquals(kalah.getStatus().get(10), finalStatus.get(9));
        assertEquals(kalah.getStatus().get(11), finalStatus.get(10));
        assertEquals(kalah.getStatus().get(12), finalStatus.get(11));
        assertEquals(kalah.getStatus().get(13), finalStatus.get(12));
        assertEquals(kalah.getStatus().get(14), finalStatus.get(13));
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
        status.put(3, 2);
        status.put(4, 1);
        status.put(13, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0), game);
    }

    @Test
    public void assertThatPlayerCanMoveTwoSeedsToFollowingPits() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 2);
        status.put(13, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0), game);
    }

    @Test
    public void assertThatPlayerCanMoveThreeSeedsToPitsWithSeeds() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 3);
        status.put(5, 7);
        status.put(10, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 1, 8, 0, 1, 0, 0, 1, 0, 0, 0, 0), game);
    }

    @Test
    public void assertThatPlayerTwoCanSeedOnPlayerOneSide() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(13, 3);
        status.put(6, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(13);

        checkFinalState(Arrays.asList(1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1), game);
    }

    @Test
    public void assertThatPlayerOneCanLoopOverBoard_DontSowPlayerTwoHouse() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(5, 10);
        status.put(13, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(5);

        checkFinalState(Arrays.asList(1, 0, 0, 0, 0, 1, 3, 1, 0, 1, 1, 1, 2, 0), game);
    }

    @Test
    public void assertThatPlayerTwoCanLoopOverBoard_DontSowPlayerOneHouse() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 1);
        status.put(12, 10);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(12);

        checkFinalState(Arrays.asList(1, 0, 2, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 3), game);
    }

    @Test
    public void assertThatPlayerOneShouldSowAgainIfEndPitHasSeeds() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(3, 5);
        status.put(4, 2);
        status.put(5, 3);
        status.put(8, 3);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(3);

        checkFinalState(Arrays.asList(0, 0, 0, 3, 4, 1, 1, 0, 1, 1, 1, 1, 0, 0), game);
    }

    @Test
    public void assertThatPlayerTwoShouldSowAgainIfEndPitHasSeeds() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(9, 3);
        status.put(13, 4);
        status.put(3, 3);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(13);

        checkFinalState(Arrays.asList(0, 1, 0, 1, 1, 1, 0, 0, 3, 0, 0, 0, 0, 3), game);
    }

    @Test
    public void assertThatPlayerOneStealsFromPlayerTwoWhenOnOwnEmptyPit() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(6, 10);
        status.put(13, 1);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(6);

        checkFinalState(Arrays.asList(1, 1, 0, 0, 0, 0, 3, 1, 1, 0, 1, 1, 2, 0), game);
    }

    @Test
    public void assertThatPlayerTwoStealsFromPlayerOneWhenOnOwnEmptyPit() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(2, 1);
        status.put(13, 10);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(13);

        checkFinalState(Arrays.asList(1, 2, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 3), game);
    }

    @Test
    public void assertThatGameWontChangeWhenMovingZeroSeeds() throws Exception {
        Map<Integer, Integer> status = createClearStatus();
        status.put(1, 3);
        status.put(4, 5);
        status.put(9, 11);
        status.put(13, 2);
        Kalah game = new Kalah();
        game.setStatus(status);

        game.playMove(2);

        checkFinalState(Arrays.asList(3, 0, 0, 5, 0, 0, 0, 0, 11, 0, 0, 0, 2, 0), game);
    }

    @Test
    public void assertThatPitCantBeLowerThan1() throws Exception {
        assertThrows(IllegalKalahMoveException.class, () -> {
            new Kalah().playMove(0);
        });
    }
}
