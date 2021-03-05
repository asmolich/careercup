import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * #Meduim #HashTable #PrefixSum
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        SubarraySumEqualsK sol = new SubarraySumEqualsK();
        System.out.println(sol.subarraySum(new int[]{1, 1, 1}, 2)); // 2
        System.out.println(sol.subarraySum(new int[]{1, 2, 3}, 3)); // 2
        System.out.println(sol.subarraySum(new int[]{1}, 0)); // 0
        System.out.println(sol.subarraySum(new int[]{-1, -1, 1}, 0)); // 1
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return k == 0 ? 1 : 0;

        int prefixSum = 0;
        int count = 0;
        Map<Integer, Integer> sumCounts = new HashMap<>();
        for (int num : nums) {
            prefixSum += num;
            if (prefixSum == k) count++;
            if (sumCounts.containsKey(prefixSum - k)) {
                count += sumCounts.get(prefixSum - k);
            }
            sumCounts.put(prefixSum, sumCounts.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
