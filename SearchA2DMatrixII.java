/**
 * LeetCode
 * 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * #Medium #BinarySearch
 */
public class SearchA2DMatrixII {
    public static void main(String[] args) {
        SearchA2DMatrixII sol = new SearchA2DMatrixII();
        System.out.println(sol.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5)); // true
        System.out.println(sol.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20)); // false
        System.out.println(sol.searchMatrix(new int[][]{{1}}, 1)); // true
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (matrix[r][c] == target) return true;
            if (matrix[r][c] < target) r++;
            else c--;
        }
        return false;
    }
}
