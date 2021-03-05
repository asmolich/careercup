import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 594. Longest Harmonious Subsequence
 * https://leetcode.com/problems/longest-harmonious-subsequence/
 * #Easy #HashTable
 */
public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        LongestHarmoniousSubsequence sol = new LongestHarmoniousSubsequence();
        System.out.println(sol.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7})); // 5
        System.out.println(sol.findLHS(new int[]{1, 2, 3, 4})); // 2
        System.out.println(sol.findLHS(new int[]{1, 1, 1, 1})); // 0
        System.out.println(sol.findLHS(new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17})); // 0
        System.out.println(sol.findLHS(new int[]{35005211, 21595368, 94702567, 26956429, 36465782, 61021530, 78722862, 33665123, 45174067, 68703135})); // 0
    }

    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxLen = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        if (min == max) return 0;
        for (var entry : counts.entrySet()) {
            Integer key = entry.getKey();
            Integer curr = counts.getOrDefault(key, 0);
            if (curr == 0) continue;
            Integer prev = counts.getOrDefault(key - 1, 0);
            if (prev == 0) continue;
            maxLen = Math.max(maxLen, curr + prev);
        }

        return maxLen;
    }
}
