import java.util.HashMap;
import java.util.Map;

/**
 * Facebook Interview Preparation
 * <p>
 * Minimum Length Substrings
 * <p>
 * You are given two strings s and t. You can select any substring of string s and rearrange the characters of the selected substring. Determine the minimum length of the substring of s such that string t is a substring of the selected substring.
 * <p>
 * Signature
 * int minLengthSubstring(String s, String t)
 * <p>
 * Input
 * s and t are non-empty strings that contain less than 1,000,000 characters each
 * <p>
 * Output
 * Return the minimum length of the substring of s. If it is not possible, return -1
 * <p>
 * Example
 * s = "dcbefebce"
 * t = "fd"'
 * output = 5
 * <p>
 * Explanation:
 * Substring "dcbef" can be rearranged to "cfdeb", "cefdb", and so on. String t is a substring of "cfdeb". Thus, the minimum length required is 5.
 */
public class MinimumLengthSubstrings {
    public static void main(String[] args) {
        MinimumLengthSubstrings sol = new MinimumLengthSubstrings();
        System.out.println(sol.minLengthSubstring("", "")); // -1
        System.out.println(sol.minLengthSubstring("dcbefebce", "fd")); // 5
        System.out.println(sol.minLengthSubstring("bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf", "cbccfafebccdccebdd")); // -1
        System.out.println(sol.minLengthSubstring("abaaacb", "abc")); // 2
    }

    public int minLengthSubstring(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) return -1;

        int m = s.length();
        int n = t.length();

//        int[] countsS = new int[26];
//        for (int i = 0; i < m; i++) {
//            char ch = s.charAt(i);
//            countsS[ch - 'a']++;
//        }

        Map<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = t.charAt(i);
            int value = mapT.getOrDefault(ch, 0) + 1;
            mapT.put(ch, value);
//            if (value > countsS[ch - 'a']) return -1;
        }
        String max = null;
        int start = 0;
        int count = mapT.size();
        for (int i = 0; i < m; i++) {
            // move i
            char ch = s.charAt(i);
            if (mapT.containsKey(ch)) {
                int value = mapT.get(ch) - 1;
                mapT.put(ch, value);
                if (value == 0) {
                    count--;
                }
            }
            // move start
            while (count <= 0) {
                ch = s.charAt(start);
                if (mapT.containsKey(ch)) {
                    int value = mapT.get(ch) + 1;
                    mapT.put(ch, value);
                    if (value >= 1) {
                        count++;
                    }
                }
                if (max == null || i - start + 1 < max.length()) {
                    max = s.substring(start, i + 1);
                }
                start++;
            }
        }
        //System.out.println(max);
        if (max == null || max.isEmpty()) return -1;

        int res = max.length();

        int commonLen = Math.min(res, n);
        int commonPrefix = 0;
        int commonSuffix = 0;
        for (int i = 0; i < commonLen; i++) {
            char ch1 = max.charAt(i);
            char ch2 = t.charAt(i);
            if (ch1 == ch2) commonPrefix++;
            else break;
        }
        for (int i = 0; i < commonLen; i++) {
            char ch1 = max.charAt(max.length() - 1 - i);
            char ch2 = t.charAt(t.length() - 1 - i);
            if (ch1 == ch2) commonSuffix++;
            else break;
        }
        return res - Math.max(commonPrefix, commonSuffix);
    }
}
