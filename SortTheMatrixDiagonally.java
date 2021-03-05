import java.util.Arrays;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode
 * 1329. Sort the Matrix Diagonally
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * #Medium #Sort
 */
public class SortTheMatrixDiagonally {
    public static void main(String[] args) {
        SortTheMatrixDiagonally sol = new SortTheMatrixDiagonally();
        System.out.println(Arrays.deepToString(sol.diagonalSort(new int[][]{
                {3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}
        }))); // [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
    }

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        //noinspection unchecked
        Queue<Integer>[] diags = new Queue[n + m - 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // i=0: 3 2 1 0
                // i=1: 4 3 2 1
                //noinspection DuplicateExpressions
                Queue<Integer> diag = diags[i + n - 1 - j];
                if (diag == null) {
                    diag = new PriorityQueue<>();
                    //noinspection DuplicateExpressions
                    diags[i + n - 1 - j] = diag;
                }
                diag.add(mat[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = Optional.ofNullable(diags[i + n - 1 - j].poll()).orElse(mat[i][j]);
            }
        }
        return mat;
    }
}
