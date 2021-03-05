/**
 * LeetCode
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 * #Easy #DP #DivideAndConquer
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        MaximumSubarray sol = new MaximumSubarray();
        System.out.println(sol.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(sol.maxSubArray(new int[]{1})); // 1
        System.out.println(sol.maxSubArray(new int[]{0})); // 0
        System.out.println(sol.maxSubArray(new int[]{-1})); // -1
        System.out.println(sol.maxSubArray(new int[]{-3, -1, -2})); // -1
        System.out.println(sol.maxSubArray(new int[]{-2147483647})); // -2147483647
        System.out.println("Divide and Conquer:");
        System.out.println(sol.maxSubArrayDC(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(sol.maxSubArrayDC(new int[]{1})); // 1
        System.out.println(sol.maxSubArrayDC(new int[]{0})); // 0
        System.out.println(sol.maxSubArrayDC(new int[]{-1})); // -1
        System.out.println(sol.maxSubArrayDC(new int[]{-3, -1, -2})); // -1
        System.out.println(sol.maxSubArrayDC(new int[]{-2147483647})); // -2147483647
    }

    // O(N)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        int sum = nums[0];
        int bestSum = sum;
        for (int i = 1; i < n; i++) {
            // [-2,1,-3,4,-1,2,1,-5,4]
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            bestSum = Math.max(bestSum, sum);
        }
        return bestSum;
    }

    // O(NlogN)
    public int  maxSubArrayDC(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return maxSubArray0(nums, 0, nums.length - 1).sum;
    }

    private static class SubArray {
        int lo, hi, sum;

        public SubArray(int lo, int hi, int sum) {
            this.lo = lo;
            this.hi = hi;
            this.sum = sum;
        }
    }

    private SubArray maxSubArray0(int[] nums, int lo, int hi) {
        if (hi == lo) // base case: only one element
            return new SubArray(lo, hi, nums[lo]);

        // divide
        int mid = lo + (hi - lo) / 2;

        // conquer
        SubArray left = maxSubArray0(nums, lo, mid);
        SubArray right = maxSubArray0(nums, mid + 1, hi);
        SubArray cross = maxCrossingSubArray0(nums, lo, mid, hi);

        // combine
        if (left.sum >= right.sum && left.sum >= cross.sum)
            return left;
        else if (right.sum >= left.sum && right.sum >= cross.sum)
            return right;
        else
            return cross;
    }

    private SubArray maxCrossingSubArray0(int[] nums, int lo, int mid, int hi) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = lo;
        int maxRight = hi;

        // Find max-subarray of A[lo..mid]
        for (int i = mid; i >= lo; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        sum = 0;
        // Find max-subarray of A[mid+1..hi]
        for (int i = mid + 1; i <= hi; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        // Return the indices i and j and the sum of two sub arrays
        return new SubArray(maxLeft, maxRight, leftSum + rightSum);
    }
}
