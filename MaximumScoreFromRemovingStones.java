/**
 * LeetCode
 * 1753. Maximum Score From Removing Stones
 * https://leetcode.com/contest/weekly-contest-227/problems/maximum-score-from-removing-stones/
 * #Medium #Math
 */
public class MaximumScoreFromRemovingStones {
    public static void main(String[] args) {
        MaximumScoreFromRemovingStones sol = new MaximumScoreFromRemovingStones();
        System.out.println(sol.maximumScore(2, 4, 6)); // 6
        System.out.println(sol.maximumScore(4, 4, 6)); // 7
        System.out.println(sol.maximumScore(1, 8, 8)); // 8
    }

    public int maximumScore(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int sum = a + b + c;
        int rest = sum - max;
        return Math.min(sum / 2, rest);
    }
}
