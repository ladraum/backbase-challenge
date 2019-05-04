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

    @Test
    public void assertThatNewGameHasIDandURI() {
        Board board = new Board();
        NewGame newGame = board.createNewGame("host", "port");

        assertNotNull(newGame.getId());
        assertNotNull(newGame.getUri());
    }

    @Test
    public void assertThatNewGameHasIDOneAndRightUI() {
        Board board = new Board();
        NewGame newGame = board.createNewGame("host", "port");

        assertEquals(newGame.getId(), Integer.valueOf(1));
        assertEquals(newGame.getUri(), "http://host:port/games/1");
    }

    @Test
    public void assertThatNewGamesAreLinkedToBoard() {
        Board board = new Board();
        board.createNewGame("host", "port");

        assertEquals(board.getGames().size(), 1);
        assertEquals(board.getGames().get(0).getGameNumber(), Integer.valueOf(1));
    }
}