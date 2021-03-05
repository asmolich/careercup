/**
 * LeetCode
 * 329. Longest Increasing Path in a Matrix
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * #Hard
 */
public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix sol = new LongestIncreasingPathInAMatrix();
        System.out.println(sol.longestIncreasingPath(new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        })); // 4
        System.out.println(sol.longestIncreasingPath(new int[][]{
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        })); // 4
        System.out.println(sol.longestIncreasingPath(new int[][]{{1}})); // 1
        System.out.println(sol.longestIncreasingPath(new int[][]{
                {7, 7, 5},
                {2, 4, 6},
                {8, 2, 0}
        })); // 4
    }

    private static final int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return -1;
        int m = matrix.length;
        int n = matrix[0].length;

        int max = 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix, i, j, dp);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    private void dfs(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] > 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int localMax = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            dfs(matrix, x, y, dp);
            localMax = Math.max(localMax, 1 + dp[x][y]);
        }
        dp[i][j] = localMax;
    }
}
