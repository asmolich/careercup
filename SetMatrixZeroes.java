import java.util.Arrays;

/**
 * LeetCode
 * 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 * #Medium
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {
        SetMatrixZeroes sol = new SetMatrixZeroes();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        sol.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix)); // [[1,0,1],[0,0,0],[1,0,1]]
        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        sol.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix)); // [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        boolean rowHasZero = false;
        boolean colHasZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) rowHasZero = true;
                    if (j == 0) colHasZero = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowHasZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (colHasZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
