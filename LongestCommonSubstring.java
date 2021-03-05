/**
 * LeetCode
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 * #Medium #DP #LCS
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        LongestCommonSubstring sol = new LongestCommonSubstring();
        System.out.println(sol.longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(sol.longestCommonSubsequence("abc", "abc")); // 3
        System.out.println(sol.longestCommonSubsequence("abc", "def")); // 0
        System.out.println(sol.longestCommonSubsequence("bsbininm", "jmjkbkjkv")); // 1
        //   b s b i n i n m
        // j 0 0 0 0 0 0 0 0
        // k 0 0 0 0 0 0 0 0
        // b 1 1 1 1 1 1 1 1
        // k 1 1 1 1 1 1 1 1
    }

    public int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) return 0;

        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        //   a b c d e
        // a 1 1 1 1 1
        // c 1 1 2 2 2
        // e 1 1 2 2 3
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ch1 = s1.charAt(i - 1);
                char ch2 = s2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
