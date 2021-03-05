import java.util.Arrays;

/**
 * LeetCode
 * 1289. Minimum Falling Path Sum II
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 * #Hard #DP
 */
public class MinimumFallingPathSumII {
    public static void main(String[] args) {
        MinimumFallingPathSumII sol = new MinimumFallingPathSumII();
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
        if (n < 2) return -1;

        int[] dp = Arrays.copyOf(a[0], n);
//        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < m; i++) {
            // min of all except j-th element
            // find two first minimums, if the first is at position j, use the second one.
            int[] mins = findTwoMinimums(dp);
//            System.out.println(Arrays.toString(mins));
            int[] newDp = new int[n];
            for (int j = 0; j < n; j++) {
                int min = mins[0] == j ? mins[1] : mins[0];
                newDp[j] = dp[min] + a[i][j];
            }
            dp = newDp;
//            System.out.println(Arrays.toString(dp));
        }
        return Arrays.stream(dp).min().orElse(-1);
    }

    private int[] findTwoMinimums(int[] a) {
        int n = a.length;
        int min1 = 0;
        int min2 = -1;
        for (int i = 0; i < n; i++) {
            if (a[min1] > a[i]) {
                min1 = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == min1) continue;
            if (min2 == -1 || a[min2] > a[i]) {
                min2 = i;
            }
        }
        return new int[]{min1, min2};
    }
}
