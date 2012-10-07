package edu.euler.service;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class EulerNumberService {
    public BigDecimal getEulerNumber(Long decimals) {
        if (decimals == null) {
            return null;
        }
        BigDecimal savedNumber = getEulerNumberFromDisk(decimals);

        if (savedNumber == null) {
            BigDecimal newNumber = calculateEulerNumber(decimals);
            return saveNewNumber(newNumber, decimals);
        } else {
            return savedNumber;
        }
    }

    private synchronized BigDecimal saveNewNumber(BigDecimal newNumber, Long decimals) {
        try (FileWriter fileWriter = new FileWriter("euler-number");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.format("%d\n%s",
                    decimals, newNumber.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newNumber;
    }

    private synchronized BigDecimal getEulerNumberFromDisk(Long decimals) {
        try (FileReader fileReader = new FileReader("euler-number");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return readEulerNumber(bufferedReader, decimals);
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal readEulerNumber(BufferedReader bufferedReader, Long decimals) throws IOException {
        Long savedDecimals = Long.parseLong(bufferedReader.readLine());
        if (savedDecimals == null || savedDecimals < decimals) {
            return null;
        }
        return new BigDecimal(bufferedReader.readLine());
    }

    private BigDecimal calculateEulerNumber(Long decimals) {
        BigDecimal number = BigDecimal.ONE;

        for (long i = 1L; i < decimals; i++) {
            number = number.add(BigDecimal.ONE.divide(factorial(BigDecimal.valueOf(i)), decimals.intValue(), RoundingMode.HALF_UP));
        }

        return number;
    }

    private static BigDecimal factorial(BigDecimal n) {
        BigDecimal result = BigDecimal.ONE;

        while (!n.equals(BigDecimal.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigDecimal.ONE);
        }

        return result;
    }
}
