/**
 * LeetCode
 * 789. Escape The Ghosts
 * https://leetcode.com/problems/escape-the-ghosts/
 * #Medium
 */
public class EscapeTheGhosts {
    public static void main(String[] args) {
        EscapeTheGhosts sol = new EscapeTheGhosts();
        System.out.println(sol.escapeGhosts(new int[][]{{1, 0}, {0, 3}}, new int[]{0, 1})); // true
        System.out.println(sol.escapeGhosts(new int[][]{{1, 0}}, new int[]{2, 0})); // false
        System.out.println(sol.escapeGhosts(new int[][]{{2, 0}}, new int[]{1, 0})); // false
        System.out.println(sol.escapeGhosts(new int[][]{{5, 0}, {-10, -2}, {0, -5}, {-2, -2}, {-7, 1}}, new int[]{7, 7})); // false
        System.out.println(sol.escapeGhosts(new int[][]{{-1, 0}, {0, 1}, {-1, 0}, {0, 1}, {-1, 0}}, new int[]{0, 0})); // true
    }

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int success = dist(target, new int[]{0, 0});
        for (int[] ghost : ghosts) {
            if (success >= dist(target, ghost)) {
                return false;
            }
        }
        return true;
    }

    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
