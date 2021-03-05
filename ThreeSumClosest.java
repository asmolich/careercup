import java.util.Arrays;

/**
 * LeetCode
 * 16. 3Sum Closest
 * https://leetcode.com/problems/3sum-closest/
 * #Medium #KSum #TwoPointer
 * https://skyxu.me/2018/08/05/a-generic-solution-to-k-sum-problems/
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest sol = new ThreeSumClosest();
        System.out.println(sol.threeSumClosest(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, -1, -3, 4, -7, 22, 50, 21}, 30)); // 30
        System.out.println(sol.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        Arrays.sort(nums);

        int n = nums.length;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < n && diff != 0; i++) {
            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff))
                    diff = target - sum;
                if (sum < target) lo++;
                else hi--;
            }
        }
        return target - diff;
    }
}

