package dev.gfxv.utils;

import dev.gfxv.Config;
import dev.gfxv.exceptions.InvalidMatrixSizeException;

public class ArgsParser {

    /**
    Returns parsed value of epsilon or default epsilon if flag wasn't provided
     */
    public static Double parseEpsilon(String[] args) throws NumberFormatException {

        for (String arg : args) {
            if (arg.contains("-e")) {
                return Double.parseDouble(arg.split("=")[1]);
            }
        }

        return Config.getDefaultEpsilon();
    }

    public static int parseMatrixSize(String[] args) throws InvalidMatrixSizeException {
        for (String arg : args) {
            if (arg.contains("-n")) {
                return Integer.parseInt(arg.split("=")[1]);
            }
        }

        throw new InvalidMatrixSizeException("Matrix size is invalid or wasn't provided");
    }

    public static String parseFile(String[] args) {
        for (String arg : args) {
            if (arg.contains("-f")) {
                return arg.split("=")[1];
            }
        }

        return null;
    }
}
