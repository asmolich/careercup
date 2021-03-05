import java.util.HashSet;
import java.util.Set;

/**
 * Facebook Interview Preparation
 * <p>
 * Matching Pairs
 * <p>
 * Given two strings s and t of length N, find the maximum number of possible matching pairs in strings s and t after swapping exactly two characters within s.
 * <p>
 * A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at the ith and jth index of s, respectively. The matching pairs of the two strings are defined as the number of indices for which s[i] and t[i] are equal.
 * <p>
 * <p>
 * Note: This means you must swap two characters at different indices.
 * Signature
 * int matchingPairs(String s, String t)
 * <p>
 * Input
 * - s and t are strings of length N
 * - N is between 2 and 1,000,000
 * <p>
 * Output
 * Return an integer denoting the maximum number of matching pairs
 * <p>
 * Example 1
 * s = "abcd"
 * t = "adcb"
 * output = 4
 * <p>
 * Explanation:
 * Using 0-based indexing, and with i = 1 and j = 3, s[1] and s[3] can be swapped, making it  "adcb".
 * <p>
 * Therefore, the number of matching pairs of s and t will be 4.
 * <p>
 * Example 2
 * s = "mno"
 * t = "mno"
 * output = 1
 * <p>
 * Explanation:
 * Two indices have to be swapped, regardless of which two it is, only one letter will remain the same. If i = 0 and j=1, s[0] and s[1] are swapped, making s = "nmo", which shares only "o" with t.
 */
public class MatchingPairs {
    public static void main(String[] args) {
        MatchingPairs sol = new MatchingPairs();
        System.out.println(sol.matchingPairs("abcd", "adcb")); // 4
        System.out.println(sol.matchingPairs("mno", "mno")); // 1
        System.out.println(sol.matchingPairs("aab", "aaa")); // 2
        System.out.println(sol.matchingPairs("aab", "aab")); // 3
        System.out.println(sol.matchingPairs("ab", "aa")); // 1
        System.out.println(sol.matchingPairs("abc", "abd")); // 1
        System.out.println(sol.matchingPairs("aabcaaabaacaaacc", "aacbaaacaabaaacc")); // 14
        System.out.println(sol.matchingPairs("abcd", "lmao")); // 1
        System.out.println(sol.matchingPairs("aabcd", "almao")); // 2
        System.out.println(sol.matchingPairs("abcdabcdabcd", "lmaolmaolmao")); // 1
    }

    public int matchingPairs(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) return 0;

        int n = s.length();
        Set<String> mismatches = new HashSet<>();
        Set<Character> charsS = new HashSet<>();
        Set<Character> charsT = new HashSet<>();
        boolean hasDuplicate = false;
        int match = 0;
        int partialMatch = 0;
        // collect mismatches
        for (int i = 0; i < n; i++) {
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if (charsS.contains(sch)) {
                hasDuplicate = true;
            } else {
                charsS.add(sch);
            }
            if (charsT.contains(tch)) {
                hasDuplicate = true;
            } else {
                charsT.add(tch);
            }
            if (sch != tch) {
                mismatches.add(sch + "" + tch);
                if (charsS.contains(tch) || charsT.contains(sch)) {
                    partialMatch++;
                }
            } else {
                match++;
            }
        }
        // if (a, b) pair can be swapper to (b, a), return match + 2
        for (String mismatch : mismatches) {
            String reverse = mismatch.charAt(1) + "" + mismatch.charAt(0);
            if (mismatches.contains(reverse)) {
                return match + 2;
            }
        }
        if (partialMatch > 0) return match + 1;
        if (!hasDuplicate) {
            // one mismatch: if (a, x) pair can be swapper to (b, x), return match - 1
            if (mismatches.size() <= 1) match--;
            // strings match: we have to swap two
            if (mismatches.size() == 0) match--;
        }
        return match;
    }
}
