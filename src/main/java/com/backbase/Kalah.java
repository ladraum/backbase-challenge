package com.backbase;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kalah {
    private Integer gameNumber;
    @Getter
    @Setter
    private List<Integer> status;

    public Kalah() {
        setStatus(new ArrayList<>(Collections.nCopies(14, 0)));
    }
}
