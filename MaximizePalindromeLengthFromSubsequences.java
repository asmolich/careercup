/**
 * LeetCode
 * 1771. Maximize Palindrome Length From Subsequences
 * https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/
 * #Hard
 * #incomplete
 */
public class MaximizePalindromeLengthFromSubsequences {
    public static void main(String[] args) {
        MaximizePalindromeLengthFromSubsequences sol = new MaximizePalindromeLengthFromSubsequences();
        System.out.println(sol.longestPalindrome("cacb", "cbba")); // 5
        System.out.println(sol.longestPalindrome("ab", "ab")); // 3
        System.out.println(sol.longestPalindrome("aa", "bb")); // 0
        System.out.println(sol.longestPalindrome("ceebeddc", "d")); // 3

        //   c a c b | c b b a
        // a 0 1 1 1   1 1 1 1
        // b 0 1 1 2   2 2 2 2
        // b 0 1 1 2   2 3 3 3
        // c 1 1 2 2   3 3 3 3
        // -
        // b 1 1 2 3   3 4 4 4
        // c 1 1 2 3   4 4 4 4
        // a 1 2 2 3   4 4 4 5
        // c 1 2 3 3   4 4 4 5

        //   c e e b e d d c | d   <--- ddd
        // d 0 0 0 0 0 1 1 1   1
        // -
        // c 1 1 1 1 1 1 1 2   2
        // d 1 1 1 1 1 2 2 2   3
        // d 1 1 1 1 1 2 3 3   4
        // e 1 2 2 2 2 2 3 3   4
        // b
        // e
        // e
        // c
    }

    public int longestPalindrome(String word1, String word2) {
        if (word1 == null || word1.isEmpty() || word2 == null || word2.isEmpty()) return 0;
        int n1 = word1.length(), n2 = word2.length(), n = n1 + n2;
        int[][] dp = new int[n + 1][n + 1];
        String w = word1 + word2;
        for (int i = 1; i <= n; i++) {
            char ch = w.charAt(n - i);
            for (int j = 1; j <= n; j++) {
                if (ch == w.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (j == n1 && i == n2 && dp[i][j] == 0) return 0;
            }
        }
        return dp[n][n];
    }
}
