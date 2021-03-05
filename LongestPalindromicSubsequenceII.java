/**
 * LeetCode
 * 1682. Longest Palindromic Subsequence II
 * https://leetcode.com/problems/longest-palindromic-subsequence-ii/
 * https://leetcode.jp/problemdetail.php?id=1682
 * https://leetcode.ca/2020-07-08-1682-Longest-Palindromic-Subsequence-II/
 * #Medium #DP
 */
public class LongestPalindromicSubsequenceII {
    public static void main(String[] args) {
        LongestPalindromicSubsequenceII sol = new LongestPalindromicSubsequenceII();
        System.out.println(sol.longestPalindromeSubseq("bbabab")); // 4
        System.out.println(sol.longestPalindromeSubseq("dcbccacdb")); // 4
    }

    // dp[i][j][k] is , k as out layer value, [i,j] longest Palindromic Subsequence in range [i,j]
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int[][][] dp = new int[n][n][26];
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1))
                dp[i][i + 1][s.charAt(i) - 'a'] = 2;
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                for (int k = 0; k < 26; k++) {
                    if (s.charAt(i) == s.charAt(j) && s.charAt(i) == ('a' + k)) {
                        for (int kk = 0; kk < 26; kk++)
                            if (kk != k)
                                dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][j - 1][kk] + 2);
                    }
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][j][k]);
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k]);
                }
            }
        }

        int ret = 0;
        for (int k = 0; k < 26; k++)
            ret = Math.max(ret, dp[0][n - 1][k]);
        return ret;
    }
}
