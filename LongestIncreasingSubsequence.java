import java.util.Arrays;

/**
 * LeetCode
 * 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * #Medium #DP #BinarySearch #LIS
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
        System.out.println(sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(sol.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // 4
        System.out.println(sol.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})); // 1
        System.out.println(sol.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9})); // 3
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // 4, 10, 4, 3, 8, 9
        // [4]
        // [4] [10]
        // [4,4] [10]
        // [4,4,3] [10]
        // [4,4,3] [10,8]
        // [4,4,3] [10,8] [9]

        // patience sort
        // ArrayList<Integer> piles = new ArrayList<>();
        int n = nums.length;
        int[] dp = new int[n];
        int size = 0;

        // sort into piles
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, size, num);
            // int i = Collections.binarySearch(piles, num);
            if (i < 0) {
                i = ~i;
            }
            if (i < size) { // piles.size()) {
                // piles.set(i, num);
                dp[i] = num;
            } else {
                // piles.add(num);
                dp[size++] = num;
            }
        }

        // return piles.size();
        return size;
    }
}