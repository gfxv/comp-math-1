package dev.gfxv.utils;

import dev.gfxv.Config;
import dev.gfxv.entities.Matrix;
import dev.gfxv.entities.SeidelSolution;
import dev.gfxv.exceptions.MaxIterationsReached;

import java.security.spec.ECParameterSpec;
import java.util.Arrays;


public class Seidel {

    public static SeidelSolution solve(Matrix matrix, Double epsilon) throws MaxIterationsReached {

        int matrixSize = matrix.getMatrix().length;
        int k = 1;
        double[] result = new double[matrixSize];
        Arrays.fill(result, 1);

        while (true) {
            double delta = 0;

            for (int i = 0; i < matrixSize; i++) {
                double s = 0;

                for (int j = 0; j < matrixSize; j++) {
                    if (j == i) {
                        continue;
                    }
                    s += matrix.getMatrix()[i][j] * result[j];
                }

                double x = (matrix.getBCoeff()[i] - s) / matrix.getMatrix()[i][i];
                double d = Math.abs(x - result[i]);
                if (d > delta) {
                    delta = d;
                }

                result[i] = x;
            }

            if (delta < epsilon) {
                return new SeidelSolution(result, k);
            }

            if (k < Config.getMaxIterations()) {
                k += 1;
                continue;
            }

            throw new MaxIterationsReached(
                    String.format("Seidel algorithm reached max amount of iterations (%d)", Config.getMaxIterations())
            );
        }

    }
}
