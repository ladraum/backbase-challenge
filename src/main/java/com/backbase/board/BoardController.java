package com.backbase.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
public class BoardController {
    @Autowired
    private Board board;

    @Autowired
    Environment environment;

    @PostMapping("/")
    NewGame createNewGame() {
        String serverPort = environment.getProperty("local.server.port");
        String host = InetAddress.getLoopbackAddress().getHostName();
        return board.createNewGame(host, serverPort);
    }
}
