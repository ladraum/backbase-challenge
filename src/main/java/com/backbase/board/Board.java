package com.backbase.board;

import com.backbase.game.Kalah;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Getter
    List<Kalah> games = new ArrayList<>();
}
