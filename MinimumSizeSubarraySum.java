/**
 * LeetCode
 * 209. Minimum Size Subarray Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * #Medium #SlidingWindow #BinarySearch
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        MinimumSizeSubarraySum sol = new MinimumSizeSubarraySum();
        System.out.println(sol.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3})); // 2
        System.out.println(sol.minSubArrayLen(11, new int[]{1, 2, 3, 4, 5})); // 3
        System.out.println(sol.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5})); // 5
        System.out.println(sol.minSubArrayLen(5, new int[]{2, 3, 1, 1, 1, 1, 1})); // 2
    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0 || s <= 0) return 0;

        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int lo = 0;
        int hi = 0;
        int sum = 0;
        while (hi < n) {
            sum += nums[hi];
//            System.out.println("sum = " + sum);
            while (lo + 1 <= hi && sum - nums[lo] >= s) sum -= nums[lo++];
            if (sum >= s) {
                minLen = Math.min(minLen, hi - lo + 1);
//                System.out.println("minLen = " + minLen + ", hi = " + hi + ", lo = " + lo);
            }
//            System.out.println("sum = " + sum);
            hi++;
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
