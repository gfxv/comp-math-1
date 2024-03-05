package dev.gfxv;

import dev.gfxv.entities.Matrix;
import dev.gfxv.exceptions.InvalidMatrixNumberException;
import dev.gfxv.exceptions.InvalidMatrixSizeException;
import dev.gfxv.utils.MatrixReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {

    private static final String basePath = "src/main/resources/";

//    @Test
//    void testDiagonallyDominant() {
//        String file = basePath + "diagonallyDominant.txt";
//        try {
//            MatrixReader matrixReader = new MatrixReader(file, 3);
//            Matrix matrix = new Matrix(matrixReader.getMatrix());
//
//            assertTrue(matrix.isDiagonallyDominant());
//
//        } catch (InvalidMatrixSizeException | FileNotFoundException | InvalidMatrixNumberException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

//    @Test
//    void testNotDiagonallyDominant() {
//        String file = basePath + "notDiagonallyDominant.txt";
//        try {
//            MatrixReader matrixReader = new MatrixReader(file, 3);
//            Matrix matrix = new Matrix(matrixReader.getMatrix());
//
//            assertFalse(matrix.isDiagonallyDominant());
//
//        } catch (InvalidMatrixSizeException | FileNotFoundException | InvalidMatrixNumberException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Test
//    void testCantBeMadeDiagDominant() {
//        String file = basePath + "cantBeMadeDiagDominant.txt";
//        try {
//            MatrixReader matrixReader = new MatrixReader(file, 3);
//            Matrix matrix = new Matrix(matrixReader.getMatrix());
//
//            assertFalse(matrix.isDiagonallyDominant());
//            assertFalse(matrix.makeDiagonallyDominant());
//
//        } catch (InvalidMatrixSizeException | FileNotFoundException | InvalidMatrixNumberException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Test
    void testInvalidValue() {
        String file = basePath + "invalidValue.txt";
        assertThrows(InvalidMatrixNumberException.class, () -> new MatrixReader(file, 3));
    }

    @Test
    void testZerosOnDiagonal() {
        String file = basePath + "zeroOnDiag.txt";
        try {
            MatrixReader matrixReader = new MatrixReader(file, 3);
            Matrix matrix = new Matrix(matrixReader.getMatrix());

            assertThrows(InvalidMatrixNumberException.class, matrix::checkDiagonalZeros);

        } catch (InvalidMatrixSizeException | FileNotFoundException | InvalidMatrixNumberException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void emptyMatrix() {
        String file = basePath + "emptyMatrix.txt";
        assertThrows(InvalidMatrixSizeException.class, () -> new MatrixReader(file, 3));
    }

}