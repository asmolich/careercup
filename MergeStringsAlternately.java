/**
 * LeetCode
 * 1768. Merge Strings Alternately
 * https://leetcode.com/problems/merge-strings-alternately/
 * #Easy
 */
public class MergeStringsAlternately {
    public static void main(String[] args) {
        MergeStringsAlternately sol = new MergeStringsAlternately();
        System.out.println(sol.mergeAlternately("abc", "pqr")); // "apbqcr"
        System.out.println(sol.mergeAlternately("ab", "pqrs")); // "apbqrs"
        System.out.println(sol.mergeAlternately("abcd", "pq")); // "apbqcd"
        System.out.println(sol.mergeAlternately("", "pq")); // "pq"
    }

    public String mergeAlternately(String w1, String w2) {
        if (w1 == null || w1.isEmpty()) return w2;
        if (w2 == null || w2.isEmpty()) return w1;

        int n1 = w1.length(), n2 = w2.length(), n = n1 + n2;
        char[] res = new char[n];
        int i = 0, j = 0, k = 0;
        while (i < n) {
            if (j < n1) {
                res[i++] = w1.charAt(j++);
            }
            if (k < n2) {
                res[i++] = w2.charAt(k++);
            }
        }
        return new String(res);
    }
}
