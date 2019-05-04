package com.backbase.board;

import com.backbase.game.Kalah;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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

    @Test
    public void assertThatBoardThrowGameNotFoundOnEmptyList() {
        Board board = new Board();
        assertThrows(GameNotFoundException.class, () -> {
            board.makeMove(1, 5);
        });
    }

    @Test
    public void assertThatBoardCanMoveOnGame() throws Exception {
        Board board = new Board();
        board.createNewGame("host", "port");

        board.makeMove(1, 5);

        Kalah game = board.getGames().get(0);

        assertEquals(game.getGameNumber(), Integer.valueOf(1));

        assertEquals(game.getStatus().get(1), Integer.valueOf(7));
        assertEquals(game.getStatus().get(2), Integer.valueOf(7));
        assertEquals(game.getStatus().get(3), Integer.valueOf(7));
        assertEquals(game.getStatus().get(4), Integer.valueOf(7));
        assertEquals(game.getStatus().get(5), Integer.valueOf(0));
        assertEquals(game.getStatus().get(6), Integer.valueOf(7));
        assertEquals(game.getStatus().get(7), Integer.valueOf(9));
        assertEquals(game.getStatus().get(8), Integer.valueOf(7));
        assertEquals(game.getStatus().get(9), Integer.valueOf(7));
        assertEquals(game.getStatus().get(10), Integer.valueOf(7));
        assertEquals(game.getStatus().get(11), Integer.valueOf(0));
        assertEquals(game.getStatus().get(12), Integer.valueOf(0));
        assertEquals(game.getStatus().get(13), Integer.valueOf(7));
        assertEquals(game.getStatus().get(14), Integer.valueOf(0));
    }

    @Test
    public void assertThatBoardThrowGameNotFoundOnNonExistingGame() {
        Board board = new Board();
        board.createNewGame("host", "port");
        assertThrows(GameNotFoundException.class, () -> {
            board.makeMove(3, 5);
        });
    }
    @Test
    public void assertThatBoardCanReturnStatusOnMove() throws Exception {
        Board board = new Board();
        board.createNewGame("host", "port");

        assertNotNull(board.makeMove(1, 5));
    }
}