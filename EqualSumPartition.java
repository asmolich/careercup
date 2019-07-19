import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode.
 */
public class EqualSumPartition {
    public static void main(String[] args) {
        System.out.println(partition(new int[]{1, 2, 3}));
    }

    private static List<List<Integer>> partition(int[] arr) {
        if (arr == null || arr.length < 2) return Collections.emptyList();

        int n = arr.length;
        int total = 0;
        for (int num : arr) total += num;

        System.out.println("Total Sum = " + total);

        if (total % 2 == 1) return Collections.emptyList();

        int m = total / 2 + 1;

        System.out.println("total / 2 + 1 = " + m);

        boolean[][] dp = new boolean[n + 1][m];
        dp[0][0] = true;

        for (int i = 1; i < n; i++) {
            dp[i][0] = true;
            int prev = arr[i - 1];
            for (int j = 1; j < m; j++) {
                boolean previousRowIsTrue = dp[i - 1][j];
                boolean isExactMatch = prev == j;
                boolean canArriveAtSum = false;

                if (j >= prev) {
                    int remainingSum = j - prev;
                    canArriveAtSum = dp[i - 1][remainingSum];
                }
                dp[i][j] = previousRowIsTrue || isExactMatch || canArriveAtSum;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + Arrays.toString(dp[i]));
        }

        return Collections.emptyList();
    }
}
