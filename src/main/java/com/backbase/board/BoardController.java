package com.backbase.board;

import com.backbase.game.FinishedGameException;
import com.backbase.game.IllegalKalahMoveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
public class BoardController {
    @Autowired
    private Board board;

    @Autowired
    Environment environment;

    @PostMapping("/games")
    NewGame createNewGame() {
        String serverPort = environment.getProperty("local.server.port");
        String host = InetAddress.getLoopbackAddress().getHostName();
        return board.createNewGame(host, serverPort);
    }

    @PutMapping("/games/{gameId}/pits/{pitId}")
    GameStatus makeMove(@PathVariable Integer gameId, @PathVariable Integer pitId)
            throws GameNotFoundException, FinishedGameException, IllegalKalahMoveException {
        String serverPort = environment.getProperty("local.server.port");
        String host = InetAddress.getLoopbackAddress().getHostName();
        return board.makeMove(gameId, pitId, host, serverPort);
    }
}
