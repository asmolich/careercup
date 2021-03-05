import java.util.Arrays;

/**
 * LeetCode
 * 698. Partition to K Equal Sum Subsets
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * #Medium #DP
 */
public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        PartitionToKEqualSumSubsets sol = new PartitionToKEqualSumSubsets();
        System.out.println(sol.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4)); // true
        System.out.println(sol.canPartitionKSubsets(new int[]{1, 1, 1, 1, 1, 1, 1, 2, 6, 6}, 3)); // true
    }

    //int iteration = 0;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //iteration = 0;
        if (nums == null || nums.length == 0 || k == 0) return false;

        int total = Arrays.stream(nums).sum();
        if (total % k != 0) return false;

        int sum = total / k;
        return dfs(nums, 0, k, 0, sum, new boolean[nums.length]);
    }

    private boolean dfs(int[] nums, int idx, int k, int currSum, int sum, boolean[] visited) {
        //System.out.println(Map.of("iteration", iteration++, "nums", Arrays.toString(nums), "idx", idx, "k", k, "currSum", currSum, "sum", sum, "visited", Arrays.toString(visited)));
        if (k == 1) return true;
        if (currSum == sum) return dfs(nums, 0, k - 1, 0, sum, visited);

        for (int i = idx; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (dfs(nums, i + 1, k, currSum + nums[i], sum, visited)) return true;
            visited[i] = false;
        }
        return false;
    }
}
