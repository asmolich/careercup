import java.util.Arrays;

/**
 * LeetCode
 * 931. Minimum Falling Path Sum
 * https://leetcode.com/problems/minimum-falling-path-sum/
 * #Medium #DP
 */
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        MinimumFallingPathSum sol = new MinimumFallingPathSum();
        System.out.println(sol.minFallingPathSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));
    }

    public int minFallingPathSum(int[][] a) {
        if (a == null || a.length == 0) return -1;

        int m = a.length;
        int n = a[0].length;

        int[] dirs = {-1,1};
        int[] dp = Arrays.copyOf(a[0], n);
        for (int i = 1; i < m; i++) {
            int[] newDp = new int[n];
            for (int j = 0; j < n; j++) {
                int min = dp[j];
                for (int dir : dirs) {
                    int x = j + dir;
                    if (x < 0 || x >= n) continue;
                    min = Math.min(min, dp[x]);
                }
                newDp[j] = min + a[i][j];
            }
            dp = newDp;
        }
        return Arrays.stream(dp).min().orElse(-1);
    }
}
