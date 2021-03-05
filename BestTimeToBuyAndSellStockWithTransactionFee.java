/**
 * LeetCode
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * #Medium #DP
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6
    }

    private static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) return 0;
        if (fee < 0) return -1;

        int size = prices.length;
        int[] buy = new int[size];
        int[] sell = new int[size];

        buy[0] = -prices[0];

        for (int i = 1; i < size; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[size - 1];
    }
}
