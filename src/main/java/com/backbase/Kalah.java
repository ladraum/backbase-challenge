package com.backbase;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Kalah {
    private Integer gameNumber;
    @Getter
    @Setter
    private Map<Integer, Integer> status;

    public Kalah() {
        Map<Integer, Integer> status = new HashMap<>();
        for (int i = 1; i <= 14; i++) {
            status.put(i, 6);
        }
        status.put(7, 0);
        status.put(14, 0);
        setStatus(status);
    }

    public boolean isGameFinished() {
        return false;
    }

    public Kalah playMove(Integer pit) throws IllegalKalahMoveException {
        validateMoveNotInHouse(pit);
        Map<Integer, Integer> status = getStatus();
        Integer seedsToMove = status.get(pit);
        status.put(pit, 0);
        status.put(pit + 1, seedsToMove);
        setStatus(status);
        return this;
    }

    private void validateMoveNotInHouse(Integer pit) throws IllegalKalahMoveException {
        if(Integer.valueOf(7).equals(pit) || Integer.valueOf(14).equals(pit)) {
            throw new IllegalKalahMoveException();
        }
    }
}
