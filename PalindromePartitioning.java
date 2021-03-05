import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning/
 * #Medium #DP #Backtracking #DFS
 * #incomplete
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        PalindromePartitioning sol = new PalindromePartitioning();
        System.out.println(sol.partition("aab")); // [["a","a","b"],["aa","b"]]
        System.out.println(sol.partition("a")); // [["a"]]
    }

    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) return Collections.emptyList();
        if (s.length() == 1) return Collections.singletonList(Collections.singletonList(s));

        List<List<String>> res = new ArrayList<>();
        int n = s.length();
        return null;
    }

    private int expand(String s, int l, int r) {
        int count = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return count;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l <= r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }
}
