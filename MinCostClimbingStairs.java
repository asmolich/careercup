/**
 * LeetCode
 * 746. Min Cost Climbing Stairs
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * #Easy #DP
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{})); // 0
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20})); // 15
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
    }

    private static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;

        int costPrev = 0;
        int costPrevPrev = 0;
        int minCost;
        for (int value : cost) {
            minCost = value + Math.min(costPrev, costPrevPrev);
            costPrevPrev = costPrev;
            costPrev = minCost;
        }
        return Math.min(costPrev, costPrevPrev);
    }
}
