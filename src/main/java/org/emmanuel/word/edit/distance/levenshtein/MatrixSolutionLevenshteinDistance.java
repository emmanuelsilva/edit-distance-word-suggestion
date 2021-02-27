package org.emmanuel.word.edit.distance.levenshtein;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.Math.max;

/**
 * Straightforward but inefficient solution to calculate the Levenshtein distance by using a matrix to compute the cost of substitution.
 **/
public class MatrixSolutionLevenshteinDistance implements LevenshteinDistance {

    public int distance(String a, String b) {
        if (a.isEmpty()) {
            return b.length();
        }

        if (b.isEmpty()) {
            return a.length();
        }

        int[][] matrix = this.buildMatrix(a, b);
        print("empty matrix:", matrix);

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = lev(i, j, a, b);
            }
        }

        print("filled out matrix:", matrix);

        return matrix[a.length()][b.length()];
    }

    private int[][] buildMatrix(String a, String b) {
        int[][] matrix = new int[a.length() + 1][b.length() + 1];

        IntStream
            .rangeClosed(0, a.length())
            .forEach(index -> matrix[index][0] = index);

        IntStream
            .rangeClosed(0, b.length())
            .forEach(index -> matrix[0][index] = index);

        return matrix;
    }

    private int lev(int i, int j, String a, String b) {
        if (min(i, j) == 0) return max(i, j);
        int differenceCost = a.charAt(i-1) != b.charAt(j-1) ? 1 : 0;

        return min(
            lev(i - 1, j, a, b) + 1,
            lev(i, j - 1, a, b) + 1,
            lev(i - 1, j - 1, a, b) + differenceCost
        );
    }
    
    private int min(int ... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

    private void print(String title, int[][] matrix) {
        System.out.println(title);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

}
