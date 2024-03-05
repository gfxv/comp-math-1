package dev.gfxv.utils;


public class Determinant {

    public static double determinant(double[][] matrix) {
        double sum = 0;
        double s;

        if (matrix.length == 1)
            return(matrix[0][0]);

        for (int i = 0; i < matrix.length; i++) {
            double[][] smaller = new double[matrix.length - 1][matrix.length - 1];
            for (int a = 1; a < matrix.length; a++) {
                for (int b = 0; b < matrix.length; b++) {
                    if (b < i) smaller[a - 1][b] = matrix[a][b];
                    else if (b > i) smaller[a - 1][b - 1] = matrix[a][b];
                }
            }

            if (i % 2 == 0) s = 1;
            else s = -1;

            sum += s * matrix[0][i] * (determinant(smaller));
        }
        return sum;
    }

}
