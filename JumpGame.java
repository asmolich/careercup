/**
 * LeetCode
 * 55. Jump Game
 * https://leetcode.com/problems/jump-game/
 * #Medium
 */
public class JumpGame {
    public static void main(String[] args) {
        JumpGame sol = new JumpGame();
        System.out.println(sol.canJump(new int[]{9, 4, 2, 1, 0, 2, 0})); // true
        System.out.println(sol.canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(sol.canJump(new int[]{3, 2, 1, 0, 4})); // false
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int lastGoodIndex = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i + nums[i] >= lastGoodIndex) {
                lastGoodIndex = i;
            }
        }
        return lastGoodIndex == 0;
    }
}
