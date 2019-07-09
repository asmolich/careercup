/**
 * LeetCode
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2})); // 3
        System.out.println(maxProfit(new int[]{1, 3, 1, 5, 1, 6})); // 7
        System.out.println(maxProfit(new int[]{1, 2, 4})); // 3
        System.out.println(maxProfit(new int[]{6, 1, 3, 2, 4, 7, 6})); // 6
        System.out.println(maxProfit(new int[]{6, 1, 3, 2, 4, 7, 6, 1, 3, 2, 4, 7})); // 12
    }

    private static int maxProfitSpaceOptimal(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;

        for (int price : prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prevSold);

        }
        return Math.max(rest, sold);
    }

    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int size = prices.length;
        int[] buy = new int[size];
        int[] sell = new int[size];

        buy[0] = -prices[0];

        for (int i = 1; i < size; i++) {
            buy[i] = Math.max(buy[i - 1], (i >= 2 ? sell[i - 2] : 0) - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[size - 1];
    }
}
