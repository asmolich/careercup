import java.util.Arrays;

/**
 * LeetCode
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 * #Medium #DP #ExpandAroundCenter
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        PalindromicSubstrings sol = new PalindromicSubstrings();
        System.out.println(sol.countSubstrings("abc")); // 3
        System.out.println(sol.countSubstrings("aaa")); // 6
        System.out.println(sol.countSubstrings2("abc")); // 3
        System.out.println(sol.countSubstrings2("aaa")); // 6
    }

    // Expand around center
    public int countSubstrings2(String s) {
        int count = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            count += expandPalindrome(s, i, i); // odd length
            count += expandPalindrome(s, i, i + 1); // even length
        }
        return count;
    }

    public int expandPalindrome(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
            count++;
        }
        return count;
    }

    public int countSubstrings(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int total = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (dp[i][j] != -1) {
                    count += dp[i][j];
                } else {
                    if (isPalindrome(s, i, j)) {
                        dp[i][j]++;
                        count++;
                    }
                }
            }
            total += count;
        }
        return total;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (end < start) return false;
        while (start < end && s.charAt(start) == s.charAt(end)) {
            start++;
            end--;
        }
        return start == end || start - end == 1;
    }
}
