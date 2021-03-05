import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode
 * 85. Maximal Rectangle
 * https://leetcode.com/problems/maximal-rectangle/
 * #Hard #DP #Stack #HashTable
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        MaximalRectangle sol = new MaximalRectangle();
        System.out.println(sol.maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        })); // 6
        System.out.println(sol.maximalRectangle(new char[][]{{}})); // 0
        System.out.println(sol.maximalRectangle(new char[][]{{'0'}})); // 0
        System.out.println(sol.maximalRectangle(new char[][]{{'1'}})); // 1
        System.out.println(sol.maximalRectangle(new char[][]{{'0', '0'}})); // 0
        System.out.println(sol.maximalRectangle(new char[][]{
                {'0', '0', '1'},
                {'1', '1', '1'}
        })); // 3
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int n = matrix[0].length;

        int max = 0;
        int[] dp = new int[n];
        for (char[] row : matrix) {
            if (row.length == 0) break;
            for (int j = 0; j < n; j++) {
                if (row[j] == '0') {
                    dp[j] = 0;
                } else {
                    dp[j] += 1;
                }
            }
            // System.out.println("dp = " + Arrays.toString(dp));
            max = Math.max(max, maxRect0(dp));
        }
        return max;
    }

    private int maxRect0(int[] dp) {
        // System.out.println("dp = " + Arrays.toString(dp));
        int n = dp.length;
        Deque<Integer> deq = new ArrayDeque<>(n);
        int max = 0;
        for (int i = 0; i < n; i++) {
            // System.out.println("deq = " + deq);
            while (!deq.isEmpty() && dp[i] < dp[deq.getLast()]) {
                int idx = deq.removeLast();
                int w = deq.isEmpty() ? i : i - deq.getLast() - 1;
                max = Math.max(max, w * dp[idx]);
            }
            deq.addLast(i);
            // System.out.println("max = " + max);
        }
        while (!deq.isEmpty()) {
            // System.out.println("deq = " + deq);
            int idx = deq.removeLast();
            int w = deq.isEmpty() ? n : n - deq.getLast() - 1;
            max = Math.max(max, w * dp[idx]);
            // System.out.println("max = " + max);
        }
        return max;
    }
}
