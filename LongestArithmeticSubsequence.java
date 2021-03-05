/**
 * LeetCode
 * 1027. Longest Arithmetic Subsequence
 * https://leetcode.com/problems/longest-arithmetic-subsequence/
 * #Medium
 */
public class LongestArithmeticSubsequence {
    public static void main(String[] args) {
        LongestArithmeticSubsequence sol = new LongestArithmeticSubsequence();
        System.out.println(sol.longestArithSeqLength(new int[]{3, 6, 9, 12})); // 4
        System.out.println(sol.longestArithSeqLength(new int[]{9, 4, 7, 2, 10})); // 3
        System.out.println(sol.longestArithSeqLength(new int[]{20, 1, 15, 3, 10, 5, 8})); // 4
    }

    public int longestArithSeqLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length < 3) return 2;

        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int j : nums) {
            min = Math.min(min, j);
            max = Math.max(max, j);
        }
        int size = max - min + 1;

        int[][] dp = new int[n][2 * size]; // account for negatives
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                int len = Math.max(dp[j][diff + size], 1);
                dp[i][diff + size] = len + 1;
                maxLen = Math.max(maxLen, len + 1);
            }
        }
        return maxLen;
    }
}
