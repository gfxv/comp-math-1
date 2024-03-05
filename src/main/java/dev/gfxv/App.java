package dev.gfxv;

import dev.gfxv.entities.Matrix;
import dev.gfxv.entities.SeidelSolution;
import dev.gfxv.exceptions.InvalidMatrixNumberException;
import dev.gfxv.exceptions.InvalidMatrixSizeException;
import dev.gfxv.exceptions.MaxIterationsReached;
import dev.gfxv.utils.ArgsParser;
import dev.gfxv.utils.Determinant;
import dev.gfxv.utils.MatrixReader;
import dev.gfxv.utils.Seidel;

import java.io.FileNotFoundException;

public class App {

    private final static String basePath = "src/main/resources/";
    private final static String file = basePath + "matrix.txt";

    public static void main(String[] args) {

        try {
            String file = ArgsParser.parseFile(args);
            Double epsilon = ArgsParser.parseEpsilon(args);
            int matrixSize = ArgsParser.parseMatrixSize(args);

            if (file == null) throw new FileNotFoundException("No such file");

            MatrixReader matrixReader = new MatrixReader(file, matrixSize);
            Matrix matrix = new Matrix(matrixReader.getMatrix());

            System.out.printf("Epsilon: %f\n", epsilon);
            System.out.println("Input matrix: ");
            System.out.println(matrix);
//            if (!matrix.isDiagonallyDominant()) {
//                System.out.println("Matrix is not diagonally dominant");
//                System.out.println("Trying to make it diagonally dominant...");
//                if (!matrix.makeDiagonallyDominant()) {
//                    System.out.println("Matrix can't be made diagonally dominant.");
//                    System.out.println();
//                    System.out.println("Exiting the program...");
//                    System.exit(0);
//
//                }
//                System.out.println();
//                System.out.println("New matrix:");
//                System.out.println(matrix);
//            }

//            System.out.println("Computing determinant...");
//            double determinant = Determinant.determinant(matrix.getMatrix());
//            System.out.printf("Determinant: %.3f\n", determinant);
//            if (determinant == 0) {
//                System.out.println("Determinant is zero, can't solve matrix");
//                System.out.println();
//                System.out.println("Exiting the program...");
//                System.exit(0);
//            }
            SeidelSolution result = Seidel.solve(matrix, epsilon);

            System.out.println();
            System.out.printf("Found solution in %d iterations\n", result.getIterationsCount());
            System.out.println("Solution:");
            for (int i = 0; i < result.getSolution().length; i++) {
                System.out.printf("x[%d] %.3f\n", i + 1, result.getSolution()[i]);
            }

        } catch (InvalidMatrixSizeException |
                 FileNotFoundException |
                 InvalidMatrixNumberException |
                 MaxIterationsReached |
                 NumberFormatException e
        ) {
            System.out.println(e.getMessage());
        }
    }
}
