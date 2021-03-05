import java.util.Arrays;

/**
 * LeetCode
 * 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * #Medium #DP
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        PartitionEqualSubsetSum sol = new PartitionEqualSubsetSum();
        System.out.println(sol.canPartition(new int[]{1, 2, 3})); // true
        System.out.println(sol.canPartition(new int[]{1, 5, 11, 5})); // true
        System.out.println(sol.canPartition(new int[]{1, 2, 3, 5})); // false
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        int n = nums.length;
        int total = Arrays.stream(nums).sum();

        if ((total & 1) == 1) return false;

        int m = total / 2 + 1;

        boolean[][] dp = new boolean[n + 1][m];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
            int prev = nums[i - 1];
            for (int j = 1; j < m; j++) {
                boolean previousRowIsTrue = dp[i - 1][j];
                boolean isExactMatch = prev == j;
                boolean canArriveAtSum = false;

                if (j >= prev) {
                    int remainingSum = j - prev;
                    canArriveAtSum = dp[i - 1][remainingSum];
                }
                dp[i][j] = previousRowIsTrue || isExactMatch || canArriveAtSum;
            }
        }

        for (int i = 0; i <= n; i++) {
            System.out.println(i + ": " + Arrays.toString(dp[i]));
        }

        return dp[n][m - 1];
    }
}
