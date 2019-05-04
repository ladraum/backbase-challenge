package com.backbase.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;

@RestController
public class BoardController {
    @Autowired
    private Board board;

    @Autowired
    Environment environment;

    @PostMapping("/")
    NewGame createNewGame(HttpServletRequest request) {
        String serverPort = environment.getProperty("local.server.port");
        String host = InetAddress.getLoopbackAddress().getHostName();
        NewGame newGame = board.createNewGame(host, serverPort);
        return newGame;
    }
}
