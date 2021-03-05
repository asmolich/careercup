/**
 * LeetCode
 * 213. House Robber II
 * https://leetcode.com/problems/house-robber-ii/
 * #Medium #DP
 */
public class HouseRobberII {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1}));
        System.out.println(rob(new int[]{1, 2}));
        System.out.println(rob(new int[]{2, 3, 2}));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{1, 2, 3, 3, 2, 6}));
        System.out.println(rob(new int[]{1, 1, 3, 6, 7, 10, 7, 1, 8, 5, 9, 1, 4, 4, 3}));
    }

    private static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;

        if (n == 1) return nums[0];

        int value = 0;
        int prevPrev = 0;
        int prev = 0;
        int value2 = 0;
        int prevPrev2 = 0;
        int prev2 = 0;
        for (int i = 2; i < n + 1; i++) {
            value = Math.max(prev, prevPrev + nums[i - 2]);
            prevPrev = prev;
            prev = value;

            value2 = Math.max(prev2, prevPrev2 + nums[i - 1]);
            prevPrev2 = prev2;
            prev2 = value2;
        }

        return Math.max(value, value2);
    }
}
