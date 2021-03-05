import java.util.Arrays;

/**
 *
 */
public class DiagonalTraverse {
    public static void main(String[] args) {
        DiagonalTraverse sol = new DiagonalTraverse();
        System.out.println(Arrays.toString(sol.findDiagonalOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }))); // [1,2,4,7,5,3,6,8,9]
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];

        int m = matrix.length;
        int n = matrix[0].length;
        int min = Math.min(m,n);
        int max = Math.max(m,n);

        int[] res = new int[m * n];
        int dir = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
//                int diagonalLen =
            }
        }
        int i = 0;
        int j = 0;

        boolean up = true;

        // Traverse the matrix till all elements get traversed
        for (int k = 0; k < m * n;) {
            // If up is true then traverse from downward to upward
            if (up) {
                for (; i >= 0 && j < n; j++, i--) {
                    res[k++] = matrix[i][j];
                }

                // Set i and j according to direction
                if (i < 0 && j <= n - 1)
                    i = 0;
                if (j == n) {
                    i = i + 2;
                    j--;
                }
            }

            // If up is 0 then traverse up to down
            else {
                for (; j >= 0 && i < m; i++, j--) {
                    res[k++] = matrix[i][j];
                }

                // Set i and j according to direction
                if (j < 0 && i <= m - 1)
                    j = 0;
                if (i == m) {
                    j = j + 2;
                    i--;
                }
            }

            // Revert the up to change the direction
            up = !up;
        }
        return res;
    }
}
