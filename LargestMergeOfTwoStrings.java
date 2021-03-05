/**
 * LeetCode
 * 1754. Largest Merge Of Two Strings
 * https://leetcode.com/problems/largest-merge-of-two-strings/
 * #Medium #MergeSort
 */
public class LargestMergeOfTwoStrings {
    public static void main(String[] args) {
        LargestMergeOfTwoStrings sol = new LargestMergeOfTwoStrings();
        System.out.println(sol.largestMerge("cabaa", "bcaaa")); // "cbcabaaaaa"
        System.out.println(sol.largestMerge("abcabc", "abdcaba")); // "abdcabcabcaba"
        System.out.println(sol.largestMerge("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"));
    }

    public String largestMerge(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int n = n1 + n2;
        char[] res = new char[n];
        int i = 0, j = 0, k = 0;
        while (j < n1 || k < n2) {
            if (compare(word1, j, word2, k) > 0) {
                res[i++] = word1.charAt(j++);
            } else {
                res[i++] = word2.charAt(k++);
            }
        }
        return new String(res);
    }

    private int compare(String word1, int i1, String word2, int i2) {
        int n1 = word1.length() - i1;
        int n2 = word2.length() - i2;
        for (int i = 0, len = Math.min(n1, n2); i < len; i++) {
            char a = word1.charAt(i1 + i);
            char b = word2.charAt(i2 + i);
            if (a != b) {
                return a - b;
            }
        }
        return n1 - n2;
    }
}
