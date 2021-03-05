/**
 * LeetCode
 * 516. Longest Palindromic Subsequence
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * #Medium #DP #LCS
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        LongestPalindromicSubsequence sol = new LongestPalindromicSubsequence();
        System.out.println(sol.longestPalindromeSubseq("bbbab")); // 4
        System.out.println(sol.longestPalindromeSubseq("cbbd")); // 2
        System.out.println(sol.longestPalindromeSubseq("qwerty")); // 1
        System.out.println(sol.longestPalindromeSubseq("racecar")); // 7
        System.out.println(sol.longestPalindromeSubseq("qrwaecretcyauri")); // 7
    }

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) return 0;

        //   b b b a b
        // b 1 1 1 1 1
        // a 1 1 1 2 2
        // b 1 2 2 2 3
        // b 1 2 3 3 3
        // b 1 2 3 3 4

        //   c b b d
        // d 0 0 0 1
        // b 0 1 1 1
        // b 0 1 2 2
        // c 1 1 2 2

        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(n - j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }
}
