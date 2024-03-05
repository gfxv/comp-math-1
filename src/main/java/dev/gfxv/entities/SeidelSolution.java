package dev.gfxv.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeidelSolution {
    private double[] solution;
    private int iterationsCount;

}
