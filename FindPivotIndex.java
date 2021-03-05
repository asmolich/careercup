/**
 * LeetCode
 * 724. Find Pivot Index
 * https://leetcode.com/problems/find-pivot-index/
 * #Easy #PrefixSum
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        FindPivotIndex sol = new FindPivotIndex();
        System.out.println(sol.pivotIndex(new int[]{1, 7, 3, 6, 5, 6})); // 3
        System.out.println(sol.pivotIndex(new int[]{1, 2, 3})); // -1
        System.out.println(sol.pivotIndex(new int[]{2, 1, -1})); // 0
    }

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        int total = prefixSums[n];
        for (int i = 0; i < n; i++) {
            if (prefixSums[i] == total - prefixSums[i + 1]) return i;
        }
        return -1;
    }
}
