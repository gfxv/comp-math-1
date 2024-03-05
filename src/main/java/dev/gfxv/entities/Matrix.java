package dev.gfxv.entities;

import dev.gfxv.exceptions.InvalidMatrixNumberException;
import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

@Getter
public class Matrix {

    private double[][] matrix;
    private double[] bCoeff;
    private int n;

    public Matrix(double[][] matrix) throws InvalidMatrixNumberException {
        this.bCoeff = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            this.bCoeff[i] = matrix[i][matrix.length];
        }

        double[][] tempMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                tempMatrix[i][j] = matrix[i][j];
            }
        }
        this.matrix = tempMatrix;
        this.n = matrix.length;

    }

    public boolean isDiagonallyDominant() {
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j != i) sum += Math.abs(matrix[i][j]);
            }

            if (Math.abs(matrix[i][i]) < sum) {
                return false;
            }
        }
        return true;
    }

    public boolean makeDiagonallyDominant() {

        double[][] newMatrix = new double[n][n];
        double[] newBCoeff = new double[n];

        for (int row = 0; row < this.matrix.length; row++) {
            DominantIndex dominantIndex = findDominantIndex(this.matrix[row]);
            if (dominantIndex.getIndex() != -1) {
                newMatrix[dominantIndex.getIndex()] = this.matrix[row];
                newBCoeff[dominantIndex.getIndex()] = this.bCoeff[row];
                continue;
            }
            return false;
        }

        this.matrix = newMatrix;
        this.bCoeff = newBCoeff;

        return true;
    }

    private DominantIndex findDominantIndex(double[] row) {

        double rowSum = Arrays.stream(row)
                .map(Math::abs)
                .sum();

        for (int i = 0; i < row.length; i++) {
            double diff = Math.abs(row[i]) * 2 - rowSum;
            if (diff >= 0)
                return new DominantIndex(i, diff != 0);

        }

        return new DominantIndex(-1, false);
    }


    public void checkDiagonalZeros() throws InvalidMatrixNumberException {
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] == 0) {
                throw new InvalidMatrixNumberException("Gauss-Seidel requires nonzero diagonal entries");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int rowIndex = 0;
        for (double[] row: matrix) {
            Arrays.stream(row).forEach(element -> stringBuilder.append(String.format("%.2f ", element)));
            stringBuilder.append("| ").append(bCoeff[rowIndex]).append("\n");
            rowIndex++;
        }
        return stringBuilder.toString();
    }
}
