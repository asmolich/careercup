import java.util.HashMap;

/**
 * LeetCode
 * 494. Target Sum
 * https://leetcode.com/problems/target-sum/
 * #Medium #DP #Memoization
 */
public class TargetSum {
    public static void main(String[] args) {
        TargetSum sol = new TargetSum();
        System.out.println(sol.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)); // 5
    }

    public int findTargetSumWays(int[] nums, int s) {
        if (nums == null || nums.length == 0) return 0;

        return findTargetSumWays0(nums, s, 0, 0, new HashMap<>());
    }

    private int findTargetSumWays0(int[] nums, int s, int idx, int sum, HashMap<Integer, HashMap<Integer, Integer>> memo) {
        if (idx >= nums.length) {
            if (sum == s) return 1;
            return 0;
        }
        HashMap<Integer, Integer> sumCount = memo.get(idx);
        if (sumCount == null) {
            sumCount = new HashMap<>();
            memo.put(idx, sumCount);
        } else if (sumCount.containsKey(sum)) {
            return sumCount.get(sum);
        }
        int count = sumCount.getOrDefault(sum, 0);
        // plus
        count += findTargetSumWays0(nums, s, idx + 1, sum + nums[idx], memo);
        // minus
        count += findTargetSumWays0(nums, s, idx + 1, sum - nums[idx], memo);
        sumCount.put(sum, count);
        return count;
    }
}
