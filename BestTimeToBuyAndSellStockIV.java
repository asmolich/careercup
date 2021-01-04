import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode
 * 188. Best Time to Buy and Sell Stock IV
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * #Hard #DP
 */
public class BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2, 4, 1}));//2
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));//7
    }

//    private static int maxProfit(int k, int[] prices) {
//        if (prices == null || prices.length < 2 || k < 1) return 0;
//
//        int[][] dp = new int[k + 1][prices.length];
//        for (int tr = 1; tr <= k; tr++) {
//            int maxDiff = -prices[0];
//            for (int day = 1; day < prices.length; day++) {
//                maxDiff = Math.max(maxDiff, dp[tr - 1][day] - prices[day]);
//                dp[tr][day] = Math.max(dp[tr][day - 1], maxDiff + prices[day]);
//            }
//        }
//        System.out.println(Arrays.toString(prices));
//        for (int i = 0; i <= k; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        printTransactions(dp, prices);
//        return dp[k][prices.length - 1];
//    }

    private static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k < 1) return 0;

        if (k >= prices.length) return solveMaxProfit(prices);
        int[] g = new int[k + 1];
        int[] l = new int[k + 1];
        for (int i = 0; i < prices.length - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                l[j] = Math.max(g[j - 1] + Math.max(diff, 0), l[j] + diff);
                g[j] = Math.max(g[j], l[j]);
            }
        }
        return g[k];
    }

    private static int solveMaxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    private static void printTransactions(int[][] dp, int[] prices) {
        Deque<Integer> deq = new ArrayDeque<>();
        System.out.println(Arrays.toString(prices));
        int tr = dp.length - 1;
        int day = dp[0].length - 1;
        while (tr > 0 && day > 0) {
            if (dp[tr][day] == dp[tr][day - 1]) {
                day--;
            } else {
                deq.addFirst(day);
                int maxDiff = dp[tr][day] - prices[day];
                for (int i = day - 1; i >= 0; i--) {
                    if (dp[tr - 1][i] - prices[i] == maxDiff) {
                        tr--;
                        day = i;
                        deq.addFirst(day);
                        break;
                    }
                }
            }
        }

        if (!deq.isEmpty()) System.out.println("Buy at price " + prices[deq.pollFirst()]);
        if (!deq.isEmpty()) System.out.println("Sell at price " + prices[deq.pollFirst()]);
    }
}
