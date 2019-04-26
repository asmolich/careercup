/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * - '.' Matches any single character
 * - '*' Matches zero or more of the preceding elements
 */
class RegularExpressionMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("", ""));                      // true
        System.out.println(isMatch("a", ""));                     // false
        System.out.println(isMatch("", "a"));                     // false
        System.out.println(isMatch("aa", "a"));                   // false
        System.out.println(isMatch("aa", "a*"));                  // true
        System.out.println(isMatch("ab", ".*"));                  // true
        System.out.println(isMatch("aab", "c*a*b"));              // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(isMatch("mississippi", "mis*is*ip*.")); // true
    }

    private static boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        if (s.equals("") && p.equals("")) return true;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        if (!p.equals("")) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 2] || dp[0][j - 1];
                } else dp[0][j] = false;
            }

            for (int i = 1; i < dp.length; i++) {  // s
                for (int j = 1; j < dp[i].length; j++) {  // p
                    char si = s.charAt(i - 1);
                    char pj = p.charAt(j - 1);
                    if (si == pj || pj == '.') dp[i][j] = dp[i - 1][j - 1];
                    else if (pj == '*')
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || ((si == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                    else dp[i][j] = false;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}

