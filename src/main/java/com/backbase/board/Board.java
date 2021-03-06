package com.backbase.board;

import com.backbase.game.FinishedGameException;
import com.backbase.game.IllegalKalahMoveException;
import com.backbase.game.Kalah;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Board {
    @Getter
    List<Kalah> games = new ArrayList<>();

    public NewGame createNewGame(String host, String port) {
        Integer newGameId = games.size() + 1;

        addNewGameToList(newGameId);

        String uri = buildGameURL(host, port, newGameId);
        return new NewGame(newGameId, uri);
    }

    private void addNewGameToList(Integer newGameId) {
        Kalah game = new Kalah();
        game.setGameNumber(newGameId);
        getGames().add(game);
    }

    public GameStatus makeMove(Integer gameIndex, Integer pit, String host, String port) throws GameNotFoundException, IllegalKalahMoveException, FinishedGameException {
        Kalah game;
        try {
            game = getGames().get(gameIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new GameNotFoundException();
        }
        game.playMove(pit);

        String url = buildGameURL(host, port, game.getGameNumber());
        return new GameStatus(game, url);
    }

    private String buildGameURL(String host, String port, Integer gameId) {
        return String.format("http://%s:%s/games/%d", host, port, gameId);
    }
}
