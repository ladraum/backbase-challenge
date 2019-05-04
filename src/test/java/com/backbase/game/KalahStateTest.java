package com.backbase.game;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KalahStateTest {

    @Test
    public void assertThatGameShouldStartNotFinished() {
        assertFalse(new Kalah().isGameFinished());
    }

    @Test
    public void assertThatGameIsFinishedWhenNoSeedsOnPlayerOneSide() {
        Kalah game = new Kalah();
        Map<Integer, Integer> status = game.getStatus();
        status.put(0, 0);
        status.put(1, 0);
        status.put(2, 0);
        status.put(3, 0);
        status.put(4, 0);
        status.put(5, 0);
        status.put(6, 0);
        game.setStatus(status);

        assertTrue(game.isGameFinished());
    }

    @Test
    public void assertThatGameIsFinishedWhenNoSeedsOnPlayerTwoSide() {
        Kalah game = new Kalah();
        Map<Integer, Integer> status = game.getStatus();
        status.put(8, 0);
        status.put(9, 0);
        status.put(10, 0);
        status.put(11, 0);
        status.put(12, 0);
        status.put(13, 0);
        status.put(14, 0);
        game.setStatus(status);

        assertTrue(game.isGameFinished());
    }

    @Test
    public void assertThatPlayerCantPlayOnFinishedGame() {
        Kalah game = new Kalah();
        Map<Integer, Integer> status = game.getStatus();
        status.put(0, 0);
        status.put(1, 0);
        status.put(2, 0);
        status.put(3, 0);
        status.put(4, 0);
        status.put(5, 0);
        status.put(6, 0);
        game.setStatus(status);

        assertThrows(FinishedGameException.class, () -> {
            new Kalah().playMove(3);
        });

    }
}
