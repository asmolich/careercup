/**
 * LeetCode
 * 1037. Valid Boomerang
 * https://leetcode.com/problems/valid-boomerang/
 * #Easy
 * <pre>
 * The area of a triangle determined by three points will be zero iff they are collinear
 * (including the degenerate cases of two or all three points being concurrent), i.e.,
 *     |x1 y1 1|
 *     |x2 y2 1| = 0
 *     |x3 y3 1|
 * or, in expanded form,
 *     x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) = 0
 * </pre>
 */
public class ValidBoomerang {
    public static void main(String[] args) {
        System.out.println(isBoomerang(new int[][]{{1, 1}, {2, 3}, {3, 2}}));
        System.out.println(isBoomerang(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }

    private static boolean isBoomerang(int[][] points) {
        if (points == null || points.length != 3) return false;

        return points[0][0] * (points[1][1] - points[2][1]) +
                points[1][0] * (points[2][1] - points[0][1]) +
                points[2][0] * (points[0][1] - points[1][1])
                != 0;
    }
}
