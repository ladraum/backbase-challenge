package com.backbase.game;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Kalah {
    private Integer gameNumber;
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

    public Kalah playMove(Integer pit) throws IllegalKalahMoveException, FinishedGameException {
        validateMoveInsideBoundaries(pit);
        validateMoveNotInHouse(pit);
        validateMoveInFinishedGame();
        sow(pit);
        return this;
    }

    public boolean isGameFinished() {
        return noSeedsOnPlayerOneSide() || noSeedsOnPlayerTwoSide();
    }

    private void validateMoveInsideBoundaries(Integer pit) throws IllegalKalahMoveException {
        if(pit < 1 || pit > 14) {
            throw new IllegalKalahMoveException();
        }
    }

    private void validateMoveNotInHouse(Integer pit) throws IllegalKalahMoveException {
        if (Integer.valueOf(7).equals(pit) || Integer.valueOf(14).equals(pit)) {
            throw new IllegalKalahMoveException();
        }
    }

    private void validateMoveInFinishedGame() throws FinishedGameException {
        if(isGameFinished()) {
            throw new FinishedGameException();
        }
    }

    private void sow(Integer pit) {
        if(getStatus().get(pit) != 0) {
            Integer houseToSkip = getHouseToSkipFromOtherPlayer(pit);
            Integer seedsOnFinalPit;
            do {
                pit = sowSeedsFromPit(pit, houseToSkip);
                seedsOnFinalPit = getStatus().get(pit);
            } while (seedsOnFinalPit != 1);

            stealPitWhenLastPitIsOwned(pit, houseToSkip);
        }
    }

    private Integer sowSeedsFromPit(Integer pit, Integer houseToSkip) {
        Map<Integer, Integer> status = getStatus();
        Integer seedsToMove = getSeedsOnPit(pit);
        clearPit(pit);
        while (seedsToMove != 0) {
            pit = seedNextPit(pit, houseToSkip);
            seedsToMove--;
        }
        setStatus(status);
        return pit;
    }

    private Integer getHouseToSkipFromOtherPlayer(Integer pit) {
        if (pit > 7) {
            return 7;
        }
        return 14;
    }

    private Integer getSeedsOnPit(Integer pit) {
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
        if (pit == houseToSkip) {
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

    private void stealPitWhenLastPitIsOwned(Integer pit, Integer houseToSkip) {
        if (isLastPitOwned(pit, houseToSkip)) {
            stealFromOppositePit(pit, houseToSkip);
        }
    }

    private boolean isLastPitOwned(Integer pit, Integer houseToSkip) {
        if (houseToSkip == 14) {
            return pit < 7;
        }
        return pit > 7 && pit < 14;
    }

    private void stealFromOppositePit(Integer pit, Integer houseToSkip) {
        Integer oppositePit = getOppositePit(pit, houseToSkip);
        Integer seedsOnOppositePit = getSeedsOnPit(oppositePit);
        Integer ownHouse = getOwnHouse(houseToSkip);

        clearPit(pit);
        clearPit(oppositePit);

        Map<Integer, Integer> status = getStatus();
        status.put(ownHouse, status.get(ownHouse) + seedsOnOppositePit + 1);
        setStatus(status);
    }

    private Integer getOppositePit(Integer pit, Integer houseToSkip) {
        if (houseToSkip == 7) {
            return pit - 7;
        }
        return pit + 7;
    }

    private Integer getOwnHouse(Integer houseToSkip) {
        if (houseToSkip == 7) {
            return 14;
        }
        return 7;
    }

    private boolean noSeedsOnPlayerOneSide() {
        return noSeedsOnPlayerSide(1, 7);
    }

    private boolean noSeedsOnPlayerTwoSide() {
        return noSeedsOnPlayerSide(8, 14);
    }

    private boolean noSeedsOnPlayerSide(int start, int end) {
        Integer seedsOnPlayerSide = 0;
        for (int i = start; i <= end; i++) {
            seedsOnPlayerSide += getStatus().get(i);
        }
        return seedsOnPlayerSide == 0;
    }
}
