import java.util.Arrays;

/**
 * LeetCode
 * 123. Best Time to Buy and Sell Stock III
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));//6
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));//4
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));//0
    }

    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int[] leftProfit = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - min);
        }

        int[] rightProfit = new int[prices.length];
        int max = prices[prices.length - 1];
        int profit = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i + 1], max - prices[i]);

            profit = Math.max(profit, leftProfit[i] + rightProfit[i]);
        }
        System.out.println(Arrays.toString(leftProfit));
        System.out.println(Arrays.toString(rightProfit));
        System.out.println(profit);
        return profit;
    }
}
