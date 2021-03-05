import java.util.Arrays;

/**
 * LeetCode
 * 1657. Determine if Two Strings Are Close
 * https://leetcode.com/problems/determine-if-two-strings-are-close/
 * #Medium #Greedy
 */
public class DetermineIfTwoStringsAreClose {
    public static void main(String[] args) {
        DetermineIfTwoStringsAreClose sol = new DetermineIfTwoStringsAreClose();
        System.out.println(sol.closeStrings("abc", "bca")); // true
        System.out.println(sol.closeStrings("a", "aa")); // false
        System.out.println(sol.closeStrings("cabbba", "abbccc")); // true
        System.out.println(sol.closeStrings("cabbba", "aabbss")); // false
        System.out.println(sol.closeStrings("xxxxxxxxxxxxxxxxxxx", "zzzzzzzzzzzzzzzzzzz")); // false
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1 == null || word1.isEmpty() || word2 == null || word2.isEmpty()) return false;

        int m = word1.length();
        int n = word2.length();
        if (m != n) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (int i = 0; i < n; i++) {
            freq1[word1.charAt(i) - 'a']++;
            freq2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] > 0 && freq2[i] == 0) || (freq2[i] > 0 && freq1[i] == 0)) return false;
        }
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
}
