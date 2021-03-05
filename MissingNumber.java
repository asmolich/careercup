/**
 * LeetCode
 * 268. Missing Number
 * https://leetcode.com/problems/missing-number/
 * #Easy
 */
public class MissingNumber {
    public static void main(String[] args) {
        MissingNumber sol = new MissingNumber();
        System.out.println(sol.missingNumber(new int[]{3, 0, 1})); // 2
        System.out.println(sol.missingNumber(new int[]{0, 1})); // 2
        System.out.println(sol.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1})); // 8
        System.out.println(sol.missingNumber(new int[]{0})); // 1
    }

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int missing = 0;
        for (int i = 0; i < n; i++) {
            missing ^= (i + 1) ^ nums[i];
        }
        return missing;
    }
}
