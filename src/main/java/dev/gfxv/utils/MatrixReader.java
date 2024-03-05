package dev.gfxv.utils;

import dev.gfxv.Config;
import dev.gfxv.exceptions.InvalidMatrixNumberException;
import dev.gfxv.exceptions.InvalidMatrixSizeException;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Data
public class MatrixReader {

    private double[][] matrix;
    private int size;

    public MatrixReader(String path, int size) throws InvalidMatrixSizeException, FileNotFoundException, InvalidMatrixNumberException {

        if (size > Config.getMaxMatrixSize()) throw new InvalidMatrixSizeException(
                String.format("Matrix size must be between %d and %d", Config.getMinMatrixSize(), Config.getMaxMatrixSize())
        );

        this.size = size;
        this.matrix = readMatrixFromFile(path);
        validateMatrix();
    }

    private double[][] readMatrixFromFile(String path) throws FileNotFoundException, InvalidMatrixNumberException, InvalidMatrixSizeException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        List<String> lines = reader.lines().toList();

        if (lines.size() < 2) {
            throw new InvalidMatrixSizeException("Matrix should be at least 2x2.");
        }

        double[][] matrix = new double[size][size + 1];

        for (int i = 0; i < size; i++) {
            String[] line = lines.get(i).split(" ");
            for (int j = 0; j < size + 1; j++) {
                double parsedNumber = parseDouble(line[j]);
                matrix[i][j] = parsedNumber;
            }
        }

        return matrix;
    }

    private void validateMatrix() throws InvalidMatrixSizeException {
        if (matrix.length > Config.getMaxMatrixSize() ||
            matrix.length < Config.getMinMatrixSize()
        ) {
            throw new InvalidMatrixSizeException(
                    String.format("Matrix size must be between %d and %d", Config.getMinMatrixSize(), Config.getMaxMatrixSize())
            );
        }
    }

    private Double parseDouble(String number) throws InvalidMatrixNumberException {
        if (number.isEmpty())
            throw new InvalidMatrixNumberException("Matrix number can't be an empty string");

        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new InvalidMatrixNumberException(String.format("Invalid matrix number: %s", number));
        }
    }
}
