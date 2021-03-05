import java.util.Map;

/**
 * LeetCode
 * 45. Jump Game II
 * https://leetcode.com/problems/jump-game-ii/
 * #Hard #Greedy
 */
public class JumpGameII {
    public static void main(String[] args) {
        JumpGameII sol = new JumpGameII();
        System.out.println(sol.jump(new int[]{2, 3, 1, 1, 4})); // 2
        System.out.println(sol.jump(new int[]{2, 3, 0, 1, 4})); // 2
        System.out.println(sol.jump(new int[]{1, 2})); // 1
        System.out.println(sol.jump(new int[]{1, 1, 1, 1})); // 3
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int incPos = 0, jumps = 0, maxPos = 0;
        for (int i = 0; i < n; i++) {
            if (i > incPos) {
                jumps++;
                incPos = maxPos;
            }
            maxPos = Math.max(maxPos, nums[i] + i);
            System.out.println(Map.of("maxPos", maxPos, "incPos", incPos, "jumps", jumps, "i", i, "nums[i]", nums[i]));
        }
        return jumps;
    }
}
