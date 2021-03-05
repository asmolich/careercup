/**
 * LeetCode
 * 152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 * #Medium #DP
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        MaximumProductSubarray sol = new MaximumProductSubarray();
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4})); // 6
        System.out.println(sol.maxProduct(new int[]{-2,0,-1})); // 0
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(nums[i], max * nums[i]);
                min = Math.min(nums[i], min * nums[i]);
            } else {
                int tmpMax = max;
                max = Math.max(nums[i], min * nums[i]);
                min = Math.min(nums[i], tmpMax * nums[i]);
            }

            result = Math.max(result, max);
        }

        return result;
    }
}
