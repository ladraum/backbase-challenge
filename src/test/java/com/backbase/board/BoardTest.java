package com.backbase.board;

import com.backbase.game.Kalah;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void assertThatBoardExists() {
        assertNotNull(new Board());
    }

    @Test
    public void assertThatNewBoardDoesntHaveGames() {
        List<Kalah> games = new Board().getGames();
        assertEquals(games.size(), 0);
    }

    @Test
    public void assertThatBoardCanCreateANewGame() {
        Board board = new Board();

        assertNotNull(board.createNewGame("host", "port"));
    }

    @Test
    public void assertThatCreateGameReturnsANewGame() {
        Board board = new Board();

        assertThat(board.createNewGame("host", "port"), instanceOf(NewGame.class));
    }
}