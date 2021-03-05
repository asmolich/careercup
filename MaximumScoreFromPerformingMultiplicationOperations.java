import java.util.Arrays;

/**
 * LeetCode
 * 1770. Maximum Score from Performing Multiplication Operations
 * https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
 * #Medium #DP
 */
public class MaximumScoreFromPerformingMultiplicationOperations {
    public static void main(String[] args) {
        MaximumScoreFromPerformingMultiplicationOperations sol = new MaximumScoreFromPerformingMultiplicationOperations();
        System.out.println(sol.maximumScore2(new int[]{1, 2, 3}, new int[]{3, 2, 1})); // 14
        System.out.println(sol.maximumScore2(new int[]{-5, -3, -3, -2, 7, 1}, new int[]{-10, -5, 3, 4, 6})); // 102
        System.out.println(sol.maximumScore2(new int[]{-3, -2, 7, 1}, new int[]{3, 4, 6})); // 37
    }

    @SuppressWarnings("unused")
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        Integer[][] memo = new Integer[m + 1][m + 1];
        return maximumScore0(nums, 0, multipliers, 0, memo);
    }

    private int maximumScore0(int[] nums, int nLo, int[] multipliers, int mIdx, Integer[][] memo) {
        if (mIdx >= multipliers.length) return 0;
        if (memo[nLo][mIdx] != null) return memo[nLo][mIdx];
        int nHi = nums.length - 1 - (mIdx - nLo);
        return memo[nLo][mIdx] = Math.max(
                multipliers[mIdx] * nums[nLo] + maximumScore0(nums, nLo + 1, multipliers, mIdx + 1, memo),
                multipliers[mIdx] * nums[nHi] + maximumScore0(nums, nLo, multipliers, mIdx + 1, memo)
        );
    }

    public int maximumScore2(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
        for (int i = 0; i <= m; i++) {
            dp[i][m - i] = 0;
        }
        for (int k = m - 1; k >= 0; k--) {
            for (int l = 0; l <= k; l++) {
                int r = k - l;
                dp[l][r] = Math.max(
                        dp[l + 1][r] + nums[l] * multipliers[k],
                        dp[l][r + 1] + nums[n - r - 1] * multipliers[k]
                );
            }
        }
        return dp[0][0];
    }
}
