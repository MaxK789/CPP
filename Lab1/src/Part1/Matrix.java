package Part1;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private int[][] matrix;
    private final int size;
    private final Random random;

    public Matrix(int size) {
        this.size = size;
        this.matrix = new int[size][size];
        this.random = new Random();
    }

    public void fillRandom() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(2 * size + 1) - size;
            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] newMatrix) {
        this.matrix = newMatrix;
    }

    public void printMatrix() {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void rearrangeByRowSum() {
        Arrays.sort(matrix, (row1, row2) -> {
            int sum1 = Arrays.stream(row1).sum();
            int sum2 = Arrays.stream(row2).sum();
            return Integer.compare(sum1, sum2);
        });
    }
}