import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 438. Find All Anagrams in a String
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * #Medium #HashTable
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        FindAllAnagramsInAString sol = new FindAllAnagramsInAString();
        System.out.println(sol.findAnagrams("cbaebabacd", "abc")); // [0,6]
        System.out.println(sol.findAnagrams("abab", "ab")); // [0,1,2]
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) return Collections.emptyList();

        int m = p.length();
        int n = s.length();
        int[] pCounts = new int[26];
        int[] sCounts = new int[26];
        for (int i = 0; i < m; i++) {
            pCounts[p.charAt(i) - 'a']++;
            sCounts[s.charAt(i) - 'a']++;
        }
        List<Integer> res = new ArrayList<>();
        if (Arrays.equals(pCounts, sCounts)) {
            res.add(0);
        }
        for (int i = m; i < n; i++) {
            sCounts[s.charAt(i - m) - 'a']--;
            sCounts[s.charAt(i) - 'a']++;
            if (Arrays.equals(pCounts, sCounts)) {
                res.add(i - m + 1);
            }
        }
        return res;
    }
}
