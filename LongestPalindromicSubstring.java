/**
 * LeetCode
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 * #Medium #DP #LCS #Manacher #ExpandAroundCenter
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring sol = new LongestPalindromicSubstring();
        System.out.println(sol.longestPalindromeLCS("babad")); // bab
        System.out.println(sol.longestPalindromeLCS("cbbd")); // bb
        System.out.println(sol.longestPalindromeLCS("a")); // a
        System.out.println(sol.longestPalindromeLCS("ac")); // a
        System.out.println(sol.longestPalindromeExpand("babad")); // aba
        System.out.println(sol.longestPalindromeExpand("cbbd")); // bb
        System.out.println(sol.longestPalindromeExpand("a")); // a
        System.out.println(sol.longestPalindromeExpand("ac")); // c
        System.out.println(sol.longestPalindromeManacher("babad")); // bab
        System.out.println(sol.longestPalindromeManacher("cbbd")); // bb
        System.out.println(sol.longestPalindromeManacher("a")); // a
        System.out.println(sol.longestPalindromeManacher("ac")); // a
        //   a c
        // c 0 1
        // a 1 1
    }

    //   b a b a d e
    // e 0 0 0 0 0 1
    // d 0 0 0 0 1 1
    // a 0 1 1 1 1 1
    // b 0 1 2 2 2 2
    // a 0 1 2 3 3 3
    // b 1 1 2 3 3 3

    public String longestPalindromeLCS(String s) {
        if (s == null || s.isEmpty()) return "";

        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        String max = "";
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                char ch1 = s.charAt(i - 1);
                char ch2 = s.charAt(n - j);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (max.length() < dp[i][j]) {
                        max = s.substring(i - dp[i][j], i);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return max;
    }

    // Expand Around Center
    public String longestPalindromeExpand(String s) {
        if (s == null || s.isEmpty()) return "";

        int n = s.length();
        int lo = 0;
        int hi = 0;
        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > hi - lo) {
                lo = i - (len - 1) / 2;
                hi = i + len / 2;
            }
        }
        return s.substring(lo, hi + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    // Manacher's algorithm O(N)
    public String longestPalindromeManacher(String s) {
        char[] t = preprocess(s);
        int[] p = new int[t.length];

        int center = 0, right = 0;
        for (int i = 1; i < t.length - 1; i++) {
            int mirror = 2 * center - i;

            if (right > i)
                p[i] = Math.min(right - i, p[mirror]);

            // attempt to expand palindrome centered at i
            while (t[i + (1 + p[i])] == t[i - (1 + p[i])])
                p[i]++;

            // if palindrome centered at i expands past right,
            // adjust center based on expanded palindrome.
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        int length = 0;   // length of longest palindromic substring
        center = 0;   // center of longest palindromic substring
        for (int i = 1; i < p.length - 1; i++) {
            if (p[i] > length) {
                length = p[i];
                center = i;
            }
        }
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

    // Transform s into t.
    // For example, if s = "abba", then t = "$#a#b#b#a#@"
    // the # are interleaved to avoid even/odd-length palindromes uniformly
    // $ and @ are prepended and appended to each end to avoid bounds checking
    private char[] preprocess(String s) {
        int n = s.length();
        char[] t = new char[n * 2 + 3];
        t[0] = '$';
        t[n * 2 + 2] = '@';
        for (int i = 0; i < n; i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[n * 2 + 1] = '#';
        return t;
    }
}
