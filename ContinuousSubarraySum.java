import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 523. Continuous Subarray Sum
 * https://leetcode.com/problems/continuous-subarray-sum/
 * #Meduim #DP
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        ContinuousSubarraySum sol = new ContinuousSubarraySum();
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); // true
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)); // true
        System.out.println(sol.checkSubarraySum(new int[]{23, 23}, 6)); // false
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, -6)); // true
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 0)); // false
        System.out.println(sol.checkSubarraySum(new int[]{0, 0}, 0)); // true
        System.out.println(sol.checkSubarraySum(new int[]{0, 0}, -1)); // true
        System.out.println(sol.checkSubarraySum(new int[]{1, 0}, 2)); // false
        System.out.println(sol.checkSubarraySum(new int[]{5, 0, 0}, 0)); // true
        System.out.println(sol.checkSubarraySum(new int[]{1, 1}, 2)); // true
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return false;

        k = Math.abs(k);

        int n = nums.length;
        int prefixSum = 0;
        boolean prevZero = false;
        Map<Integer, Integer> modIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (prevZero) return true;
                else prevZero = true;
            } else {
                prevZero = false;
            }

            prefixSum += nums[i];
            if (k != 0) prefixSum %= k;
            if (prefixSum == 0 && i > 0) {
                return true;
            }

            if (modIndex.containsKey(prefixSum)) {
                int idx = modIndex.get(prefixSum);
//                System.out.println(Map.of("i", i, "idx", idx));
                if (i - idx >= 2) return true;
            } else {
                modIndex.put(prefixSum, i);
            }
//            System.out.println(Map.of("modIndex", modIndex, "prefixSum", prefixSum));
        }
        return false;
    }
}
