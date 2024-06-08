package Part1;

import Part1.Matrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixTest {

    @Test
    public void testRearrangeByRowSum() {
        int[][] initialMatrix = {
                {2, -2, 3},//3
                {1, 2, -3},//0
                {-2, -3, 1}//-4
        };

        int[][] expectedMatrix = {
                {-2, -3, 1},//-4
                {1, 2, -3},//0
                {2, -2, 3}//3
        };

        Matrix matrix = new Matrix(3);
        matrix.setMatrix(initialMatrix);
        matrix.rearrangeByRowSum();

        assertArrayEquals(expectedMatrix, matrix.getMatrix());
    }
}