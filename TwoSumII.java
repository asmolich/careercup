import java.util.Arrays;

/**
 * LeetCode
 * 167. Two Sum II - Input array is sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * #Easy #KSum #BinarySearch
 * https://skyxu.me/2018/08/05/a-generic-solution-to-k-sum-problems/
 */
public class TwoSumII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{}, 1)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [1,2]
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 4}, 6))); // [1,3]
        System.out.println(Arrays.toString(twoSum(new int[]{-1, 0}, -1))); // [1,2]
    }

    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target - nums[i]) {
                if (i + 1 < n && nums[i + 1] == target - nums[i]) return new int[]{i + 1, i + 2}; // one-based
            } else {
                int idx = Arrays.binarySearch(nums, target - nums[i]);
                if (idx >= 0) return new int[]{Math.min(idx, i) + 1, Math.max(idx, i) + 1}; // one-based
            }
        }
        return new int[0];
    }
}
