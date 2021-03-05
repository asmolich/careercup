import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * LeetCode
 * 1755. Closest Subsequence Sum
 * https://leetcode.com/problems/closest-subsequence-sum/
 * #Hard #DivideAndCoquer #MeetInTheMiddle
 * https://en.wikipedia.org/wiki/Subset_sum_problem
 */
public class ClosestSubsequenceSum {
    public static void main(String[] args) {
        ClosestSubsequenceSum sol = new ClosestSubsequenceSum();
        System.out.println(sol.minAbsDifference(new int[]{5, -7, 3, 5}, 6)); // 0
        System.out.println(sol.minAbsDifference(new int[]{7, -9, 15, -2}, -5)); // 1
        System.out.println(sol.minAbsDifference(new int[]{1, 2, 3}, -7)); // 7
        System.out.println(sol.minAbsDifference(new int[]{-4816, 3637, 8511, -1731, -5728, -9723, 8373, -8758}, 12826)); // 236
        System.out.println(sol.minAbsDifference(new int[]{-7933, -1642, -6137, 6234, 4728, 5474, 2439}, -428059487)); // 428043775
        System.out.println(sol.minAbsDifference(new int[]{-6651, 401, -8998, -9269, -9167, 7741, -9699}, 30536)); // 22394
        System.out.println(sol.minAbsDifference(new int[]{9152249, 8464156, -2493402, 8990685, -7257152, -1050240, 2243737, -82901, 8939692}, 26915229)); // 8405
    }

    // The naive solution is to check all possible subsequences. This works in O(2^n).
    // Divide the array into two parts of nearly is equal size.
    // Consider all subsets of one part and make a list of all possible subset sums and sort this list.
    // Consider all subsets of the other part, and for each one, let its sum = x, do binary search to get
    // the nearest possible value to goal - x in the first part.
    public int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int half = n / 2;

        TreeSet<Integer> left = new TreeSet<>();
        generateSums(nums, 0, half, 0, left);
        HashSet<Integer> right = new HashSet<>();
        generateSums(nums, half, n, 0, right);

        int minDiff = Integer.MAX_VALUE;
        for (int r : right) {
            int key = goal - r;

            Integer larger = left.ceiling(key);
            Integer smaller = left.floor(key);

            if (larger != null) minDiff = Math.min(minDiff, larger - key);
            if (smaller != null) minDiff = Math.min(minDiff, key - smaller);
        }

        return minDiff;
    }

    private void generateSums(int[] nums, int idx, int toExclusive, int sum, Collection<Integer> res) {
        if (idx >= toExclusive) {
            res.add(sum);
            return;
        }
        generateSums(nums, idx + 1, toExclusive, sum, res);
        generateSums(nums, idx + 1, toExclusive, sum + nums[idx], res);
    }
}
