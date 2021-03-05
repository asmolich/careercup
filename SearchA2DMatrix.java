/**
 * LeetCode
 * 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 * #Medium #BinarySearch
 */
public class SearchA2DMatrix {
    public static void main(String[] args) {
        SearchA2DMatrix sol = new SearchA2DMatrix();
        System.out.println(sol.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3)); // true
        System.out.println(sol.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13)); // false
        System.out.println(sol.searchMatrix(new int[][]{{1}}, 1)); // true
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int r = mid / n;
            int c = mid % n;
            if (matrix[r][c] == target) return true;
            if (matrix[r][c] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
