import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 446. Arithmetic Slices II - Subsequence
 * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/description/
 * #Hard #DP
 */
public class ArithmeticSlicesII {
    public static void main(String[] args) {
        ArithmeticSlicesII sol = new ArithmeticSlicesII();
        System.out.println(sol.numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10})); // 7
        System.out.println(sol.numberOfArithmeticSlices(new int[]{7, 7, 7, 7})); // 5
    }

    public int numberOfArithmeticSlices(int[] a) {
        if (a == null || a.length == 0) return 0;

        // n ≤ 1000
        int n = a.length;
        //noinspection unchecked
        Map<Integer, Integer>[] dp = new Map[n];

        int total = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long delta = (long) a[i] - a[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = dp[j].getOrDefault(diff, 0);
                int origin = dp[i].getOrDefault(diff, 0);
                dp[i].put(diff, origin + sum + 1);
                total += sum;
            }
        }
        System.out.println(Arrays.toString(dp));
        return total;
    }
}
