/**
 * LeetCode
 * 485. Max Consecutive Ones
 * https://leetcode.com/problems/max-consecutive-ones/
 * #Easy
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        MaxConsecutiveOnes sol = new MaxConsecutiveOnes();
        System.out.println(sol.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1})); // 3
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 0) {
                max = Math.max(max, count);
                count = 0;
            } else {
                count++;
            }
        }
        return Math.max(max, count);
    }
}
