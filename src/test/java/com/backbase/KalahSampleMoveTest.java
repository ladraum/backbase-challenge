package com.backbase;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KalahSampleMoveTest {
    @Test
    public void assertThatPlayerOneCanStartGameFromPit5() throws Exception {
        Kalah game = new Kalah();

        game.playMove(5);

        checkFinalState(Arrays.asList(7, 7, 7, 7, 0, 7, 9, 7, 7, 7, 0, 0, 7, 0), game);
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

    @Test
    public void assertThatPlayerTwoCanStartGameFromPit9() throws Exception {
        Kalah game = new Kalah();

        game.playMove(9);

        checkFinalState(Arrays.asList(0, 0, 7, 7, 7, 7, 0, 7, 0, 7, 7, 7, 7, 9), game);
    }

    @Test
    public void assertThatGameStateShouldBeRightAfterMovesOn_5_9() throws Exception {
        Kalah game = new Kalah();

        game.playMove(5);
        game.playMove(9);

        checkFinalState(Arrays.asList(9, 1, 9, 0, 2, 9, 9, 9, 0, 10, 0, 2, 0, 12), game);
    }

    @Test
    public void assertThatGameStateShouldBeRightAfterMovesOn_5_9_2() throws Exception {
        Kalah game = new Kalah();

        game.playMove(5);
        game.playMove(9);
        game.playMove(2);

        checkFinalState(Arrays.asList(9, 0, 0, 1, 3, 10, 10, 10, 1, 11, 1, 3, 1, 12), game);
    }
}
