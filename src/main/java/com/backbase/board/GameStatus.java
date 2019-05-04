package com.backbase.board;

import com.backbase.game.Kalah;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class GameStatus {
    private Integer id;
    private String url;
    private Map<Integer, Integer> status;

    public GameStatus(Kalah kalah, String url) {
        setId(kalah.getGameNumber());
        setUrl(url);
        setStatus(kalah.getStatus());
    }
}
