import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode
 * 1751. Maximum Number of Events That Can Be Attended II
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/
 * #Medium #DP
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/discuss/1063845/Java-short-Solution-!!!
 */
public class MaximumNumberOfEventsThatCanBeAttendedII {
    public static void main(String[] args) {
        MaximumNumberOfEventsThatCanBeAttendedII sol = new MaximumNumberOfEventsThatCanBeAttendedII();
        System.out.println(sol.maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2)); // 7
        System.out.println(sol.maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 10}}, 2)); // 10
        System.out.println(sol.maxValue(new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}}, 3)); // 9
    }

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt((int[] a) -> a[0]).thenComparing(a -> a[1]));
        int[][] memo = new int[events.length + 1][k + 1];
        return bt(events, 0, k, memo);
    }

    int bt(int[][] events, int idx, int k, int[][] memo) {
        if (k == 0 || idx == events.length) return 0;
        if (memo[idx][k] > 0) return memo[idx][k];

        int nxt_pos = idx;
        while (nxt_pos < events.length && events[nxt_pos][0] <= events[idx][1]) nxt_pos++;
        return memo[idx][k] = Math.max(bt(events, nxt_pos, k - 1, memo) + events[idx][2], bt(events, idx + 1, k, memo));
    }
}