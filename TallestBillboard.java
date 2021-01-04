/**
 * LeetCode 956. Tallest Billboard
 * https://leetcode.com/problems/tallest-billboard/
 * #Hard #DFS
 */
public class TallestBillboard {
    static int max = 0;

    public static int tallestBillboard(int[] rods) {
        max = 0;
        if (rods == null || rods.length == 0) return 0;

        int n = rods.length;
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }

        dfs(rods, n - 1, 0, 0, sum);

        return max;
    }

    private static void dfs(int[] rods, int index, int left, int right, int rest) {
        if (index < 0) { // done
            if (left == right && left > max) {
                max = left;
            }
            return;
        }

        // throw the setup out if the new setup is lower than 2max or the gap can't be filled by the rest
        if (left + right + rest <= 2 * max || Math.abs(right - left) > rest) return;

        dfs(rods, index - 1, left + rods[index], right, rest - rods[index]);
        dfs(rods, index - 1, left, right + rods[index], rest - rods[index]);
        dfs(rods, index - 1, left, right, rest - rods[index]);
    }

    public static void main(String[] args) {
        System.out.println(tallestBillboard(new int[]{1, 2, 3, 6})); // 6
        System.out.println(tallestBillboard(new int[]{1,2,3,4,5,6})); // 10
        System.out.println(tallestBillboard(new int[]{1, 2})); // 0
    }
}
