import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode
 * 128. Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * #Hard #UnionFind #HashTable
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        LongestConsecutiveSequence sol = new LongestConsecutiveSequence();
        System.out.println(sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})); // 4
        System.out.println(sol.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // 9
        System.out.println(sol.longestConsecutive(new int[]{0})); // 1
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int max = 1;
        for (int num : nums) {
            if (!set.contains(num - 1)) { // sequence start
                for (int j = 1; j < n; j++) {
                    if (set.contains(num + j)) {
                        max = Math.max(max, j + 1);
                    } else break;
                }
            }
        }
        return max;
    }
}
