/**
 * LeetCode
 * 747. Largest Number At Least Twice of Others
 * https://leetcode.com/problems/largest-number-at-least-twice-of-others/
 * #Easy
 */
public class LargestNumberAtLeastTwiceOfOthers {
    public static void main(String[] args) {
        LargestNumberAtLeastTwiceOfOthers sol = new LargestNumberAtLeastTwiceOfOthers();
        System.out.println(sol.dominantIndex(new int[]{3, 6, 1, 0})); // 1
        System.out.println(sol.dominantIndex(new int[]{1, 2, 3, 4})); // -1
        System.out.println(sol.dominantIndex(new int[]{1})); // 0
    }

    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;

        int n = nums.length;
        int maxIndex = 0;
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > secondMax && nums[i] < max) {
                secondMax = nums[i];
            }
        }
        return max >= 2 * secondMax ? maxIndex : -1;
    }
}
