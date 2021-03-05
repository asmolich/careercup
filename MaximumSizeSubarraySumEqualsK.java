import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 325. Maximum Size Subarray Sum Equals k
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k
 * https://leetcode.com/discuss/interview-question/348632/Google-or-Phone-Screen-or-Max-Size-Subarray-Sum-Equals-k
 * https://www.cnblogs.com/grandyang/p/5336668.html
 * #Meduim #PrefixSum
 * <p>
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * <p>
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Example 1:
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * Example 2:
 * <p>
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK {
    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK sol = new MaximumSizeSubarraySumEqualsK();
        System.out.println(sol.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3)); // 4
        System.out.println(sol.maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1)); // 2
    }

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        int max = 0;
        Map<Integer, Integer> sumIndex = new HashMap<>();
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            if (prefixSum == k) max = i + 1; // can grow up to entire array
            else if (sumIndex.containsKey(prefixSum - k)) {
                int idx = sumIndex.get(prefixSum - k);
                max = Math.max(max, i - idx);
            }

            if (!sumIndex.containsKey(prefixSum)) sumIndex.put(prefixSum, i);
            System.out.println(Map.of("prefixSum", prefixSum, "sumIndex", sumIndex, "max", max));
        }
        return max;
    }
}
