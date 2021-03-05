import java.util.Arrays;

/**
 * LeetCode
 * 1673. Find the Most Competitive Subsequence
 * https://leetcode.com/problems/find-the-most-competitive-subsequence/
 * #Medium #Stack
 */
public class FindTheMostCompetitiveSubsequence {
    public static void main(String[] args) {
        FindTheMostCompetitiveSubsequence sol = new FindTheMostCompetitiveSubsequence();
        System.out.println(Arrays.toString(sol.mostCompetitive(new int[]{3, 5, 2, 6}, 2))); // [2,6]
        System.out.println(Arrays.toString(sol.mostCompetitive(new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4))); // [2,3,3,4]
        System.out.println(Arrays.toString(sol.mostCompetitive(new int[]{6, 4, 3, 3, 5, 2, 9, 6}, 4))); // [3,2,9,6]
    }

    public int[] mostCompetitive(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        // [6, 4, 3, 3, 5, 2, 9, 6] k=4
        // 3296
        // |^-global min
        // ^-local min left from global min

        int n = nums.length;
        int[] res = new int[k];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            while (idx > 0 && nums[i] < res[idx - 1] && idx + n - i > k) {
                idx--;
            }
            if (idx < k) res[idx++] = nums[i];
        }
        return res;
    }
}
