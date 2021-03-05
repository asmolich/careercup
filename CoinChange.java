import java.util.Arrays;

/**
 * LeetCode
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 * #Medium #DP
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange sol = new CoinChange();
        System.out.println(sol.coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(sol.coinChange(new int[]{2}, 3)); // -1
        System.out.println(sol.coinChange(new int[]{1}, 0)); // 0
        System.out.println(sol.coinChange(new int[]{1}, 1)); // 1
        System.out.println(sol.coinChange(new int[]{1}, 2)); // 2
    }

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
