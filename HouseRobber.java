/**
 * LeetCode
 * 198. House Robber
 * https://leetcode.com/problems/house-robber/
 * #Medium #DP
 */
public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }

    private static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int value = 0;
        int prevPrev = 0;
        int prev = 0;
        for (int i = 2; i < n + 2; i++) {
            value = Math.max(prev, prevPrev + nums[i - 2]);
            prevPrev = prev;
            prev = value;
        }
        return value;
    }
}
