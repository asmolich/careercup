import java.util.Arrays;

/**
 * LeetCode. 1099. Two Sum Less Than K
 * https://leetcode.com/problems/two-sum-less-than-k/
 *
 * <p>
 * Given an array A of integers and integer K, return the maximum S such that
 * there exists i < j with A[i] + A[j] = S and S < K.
 * If no i, j exist satisfying this equation, return -1.
 * </p>
 * <pre>
 * Example 1:
 * Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation:
 * We can use 34 and 24 to sum 58 which is less than 60.
 * Example 2:
 *
 * Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation:
 * In this case it's not possible to get a pair sum less that 15.
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 * </pre>
 */
public class TwoSumLessThanK {
    public static void main(String[] args) {
        System.out.println(twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60)); // 58
        System.out.println(twoSumLessThanK(new int[]{10, 20, 30}, 15)); // -1
    }

    private static int twoSumLessThanK(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        Arrays.sort(nums);

        int lo = 0, hi = nums.length - 1;
        int maxSum = -1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum >= target) hi--;
            else {
                maxSum = Math.max(maxSum, sum);
                lo++;
            }
        }

        return maxSum;
    }
}
