/**
 * LeetCode
 * 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * #Easy #Greedy
 */
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 7
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5})); // 4
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1})); // 0
    }

    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }

        return profit;
    }
}
