import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 1679. Max Number of K-Sum Pairs
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 * #Medium #HashTable
 */
public class MaxNumberOfKSumPairs {
    public static void main(String[] args) {
        MaxNumberOfKSumPairs sol = new MaxNumberOfKSumPairs();
        System.out.println(sol.maxOperations(new int[]{1, 2, 3, 4}, 5)); // 2
        System.out.println(sol.maxOperations(new int[]{3, 1, 3, 4, 3}, 6)); // 1
        System.out.println(sol.maxOperations(new int[]{2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2}, 3)); // 4
    }

    public int maxOperations(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            Integer pair = freq.get(k - num);
            if (pair != null && pair > 0) {
                freq.put(k - num, pair - 1);
                count++;
            } else {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }
        return count;
    }
}
