import java.util.Arrays;

/**
 * LeetCode
 * 259: 3Sum Smaller
 * https://leetcode.com/problems/3sum-smaller/
 * https://baihuqian.github.io/2018-07-28-3sum-smaller/
 * http://buttercola.blogspot.com/2015/08/leetcode.html
 * #Medium #KSum #TwoPointer
 * https://skyxu.me/2018/08/05/a-generic-solution-to-k-sum-problems/
 */
public class ThreeSumSmaller {
    public static void main(String[] args) {
        ThreeSumSmaller sol = new ThreeSumSmaller();
        System.out.println(sol.threeSumSmaller(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, -1, -3, 4, -7, 22, 50, 21}, 30)); // 29
        System.out.println(sol.threeSumSmaller(new int[]{-1, 2, 1, -4}, 1)); // -1
        System.out.println(sol.threeSumSmaller(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60)); // 59
    }

    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return -1;

        Arrays.sort(nums);

        int n = nums.length;
        int maxSum = nums[0] + nums[1] + nums[2];
        if (maxSum > target) return -1;
        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum >= target) hi--;
                else {
                    maxSum = Math.max(maxSum, sum);
                    lo++;
                }
            }
        }
        return maxSum;
    }
}

