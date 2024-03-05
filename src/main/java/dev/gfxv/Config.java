package dev.gfxv;

import lombok.Getter;


public class Config {

    @Getter
    private static final Double defaultEpsilon = 0.01;

    @Getter
    private static final int maxMatrixSize = 20;

    @Getter
    private static final int minMatrixSize = 2;

    @Getter
    private static final int maxIterations = 1000;

}
