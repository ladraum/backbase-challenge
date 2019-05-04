package com.backbase;

import org.junit.jupiter.api.Test;

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
}
