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
        sowSeedsFromPit(pit);
        return this;
    }

    private void sowSeedsFromPit(Integer pit) {
        Map<Integer, Integer> status = getStatus();
        Integer seedsToMove = getSeedsToMove(pit);
        Integer houseToSkip = 14;
        if (pit > 7) {
            houseToSkip = 7;
        }
        clearPit(pit);
        while (seedsToMove != 0) {
            pit = seedNextPit(pit, houseToSkip);
            seedsToMove--;
        }
        setStatus(status);
    }

    private void validateMoveNotInHouse(Integer pit) throws IllegalKalahMoveException {
        if (Integer.valueOf(7).equals(pit) || Integer.valueOf(14).equals(pit)) {
            throw new IllegalKalahMoveException();
        }
    }

    private Integer getSeedsToMove(Integer pit) {
        return getStatus().get(pit);
    }

    private void clearPit(Integer pit) {
        getStatus().put(pit, 0);
    }

    private Integer seedNextPit(Integer pit, Integer houseToSkip) {
        Map<Integer, Integer> status = getStatus();
        pit = getNextNotHousePit(pit, houseToSkip);
        status.put(pit, status.get(pit) + 1);
        setStatus(status);
        return pit;
    }

    private Integer getNextNotHousePit(Integer pit, Integer houseToSkip) {
        pit = getNextPit(pit);
        if(pit == houseToSkip) {
            pit = getNextPit(pit);
        }
        return pit;
    }

    public Integer getNextPit(Integer pit) {
        pit++;
        if (pit == 15) {
            pit = 1;
        }
        return pit;
    }
}
