import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 974. Subarray Sums Divisible by K
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * #Medium #HashTable #PrefixSum
 */
public class SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        SubarraySumsDivisibleByK sol = new SubarraySumsDivisibleByK();
        System.out.println(sol.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // 7
        System.out.println(sol.subarraysDivByK(new int[]{-1, 2, 9}, 2)); // 2
        System.out.println(sol.subarraysDivByK(new int[]{2, -2, 2, -4}, 6)); // 2
    }

    public int subarraysDivByK(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> prefixSums = new HashMap<>();
        int prefixSum = 0;
        int count = 0;
        for (int num : nums) {
            prefixSum += num;
            int key = prefixSum % k;
            if (key < 0) key += k;
            if (prefixSums.containsKey(key)) {
                count += prefixSums.get(key);
            }
            prefixSums.put(key, prefixSums.getOrDefault(key, 0) + 1);
        }
        if (prefixSums.containsKey(0)) count += prefixSums.get(0);
        return count;
    }
}
