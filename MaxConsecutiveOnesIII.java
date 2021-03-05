/**
 * LeetCode
 * 1004. Max Consecutive Ones III
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * #Medium #SlidingWindow
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        MaxConsecutiveOnesIII sol = new MaxConsecutiveOnesIII();
        System.out.println(sol.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2)); // 6
        System.out.println(sol.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3)); // 10
    }

    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        int lo = 0;
        int zeroes = 0;
        for (int hi = 0; hi < n; hi++) {
            if (nums[hi] == 0) zeroes++;
            while (zeroes > k) {
                if (nums[lo] == 0) zeroes--;
                lo++;
            }
            max = Math.max(max, hi - lo + 1);
        }
        return max;
    }
}
