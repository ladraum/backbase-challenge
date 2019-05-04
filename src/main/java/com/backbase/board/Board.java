package com.backbase.board;

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

        Kalah game = new Kalah();
        game.setGameNumber(newGameId);
        games.add(game);

        String uri = String.format("http://%s:%s/games/%d", host, port, newGameId);
        return new NewGame(newGameId, uri);
    }

    public void makeMove(Integer gameIndex) throws GameNotFoundException {
        throw new GameNotFoundException();
    }
}
