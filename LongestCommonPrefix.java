/**
 * LeetCode
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 * #Easy
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix sol = new LongestCommonPrefix();
        System.out.println(sol.longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // "fl"
        System.out.println(sol.longestCommonPrefix(new String[]{"dog", "racecar", "car"})); // ""
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        int n = strs.length;
        String prefix = strs[0];
        for (int i = 1; i < n; i++) {
            prefix = getPrefix(prefix, strs[i]);
            if (prefix.equals("")) return "";
        }
        return prefix;
    }

    private String getPrefix(String s1, String s2) {
        int end = 0;
        int n = Math.min(s1.length(), s2.length());
        boolean mismatch = false;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                end = i;
                mismatch = true;
                break;
            }
        }
        if (!mismatch) {
            if (s1.length() <= s2.length()) return s1;
            else return s2;
        }
        return s1.substring(0, end);
    }
}
