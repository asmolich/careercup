import java.util.Arrays;

/**
 * LeetCode
 * 238. Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 * #Medium
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        ProductOfArrayExceptSelf sol = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(sol.productExceptSelf(new int[]{1, 2, 3, 4}))); // [24,12,8,6]
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        // nums = [1, 2, 3, 4]
        //  out = [24,12,8, 6]
        //  res = [1, 1, 2, 6]

        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // System.out.println(Arrays.toString(res));
        int mult = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= mult;
            mult *= nums[i];
        }
        return res;
    }
}
