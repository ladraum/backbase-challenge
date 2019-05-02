package com.backbase;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Kalah {
    private Integer gameNumber;
    private List<Integer> status;

    public Kalah() {
        setStatus(new ArrayList<>(Collections.nCopies(14, 0)));
    }
}
