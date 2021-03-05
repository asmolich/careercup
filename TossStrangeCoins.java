/**
 * LeetCode
 * 1230. Toss Strange Coins
 * https://leetcode.com/problems/toss-strange-coins/
 * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1230-toss-strange-coins/
 * #Meduim #Probability #DP
 * <p>
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
 * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
 */
public class TossStrangeCoins {
    public static void main(String[] args) {
        TossStrangeCoins sol = new TossStrangeCoins();
        System.out.println(sol.probabilityOfHeads(new double[]{0.4}, 1));
        System.out.println(sol.probabilityOfHeads(new double[]{0.5, 0.5, 0.5, 0.5, 0.5}, 0));
    }

    public double probabilityOfHeads(double[] prob, int target) {
        if (prob == null || prob.length == 0 || target < 0 || target > prob.length) return 0;

        int n = prob.length;
        double[] dp = new double[target + 1];
        // dp[i][j] := prob of j coins face up after tossing first i coins
        // dp[i][j] = dp[i-1][j] * (1 – p[i]) + dp[i-1][j-1] * p[i]
        dp[0] = 1.0;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(i + 1, target); j >= 0; j--) {
                dp[j] = dp[j] * (1 - prob[i]) + (j > 0 ? dp[j - 1] : 0) * prob[i];
            }
        }
        return dp[target];
    }
}
