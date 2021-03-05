/**
 * LeetCode
 * 377. Combination Sum IV
 * https://leetcode.com/problems/combination-sum-iv/
 * #Medium #DP
 */
public class CombinationSumIV {
    public static void main(String[] args) {
        CombinationSumIV sol = new CombinationSumIV();
        System.out.println(sol.combinationSum4(new int[]{1, 2, 3}, 4)); // 7
    }

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target == 0) return 0;

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    // current value + remaining target sum combinations
                    dp[i] += dp[i - num]; // i-nums[j] gives new target eg (4-1) = 3
                }
            }
        }
        return dp[target];
    }
}
