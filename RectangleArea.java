/**
 * LeetCode
 * 223. Rectangle Area
 * https://leetcode.com/problems/rectangle-area/
 * #Medium
 */
public class RectangleArea {
    public static void main(String[] args) {
        RectangleArea sol = new RectangleArea();
        System.out.println(sol.computeArea(-3, 0, 3, 4, 0, -1, 9, 2)); // 45
        System.out.println(sol.computeArea(-2, -2, 2, 2, 3, 3, 4, 4)); // 17
        System.out.println(sol.computeArea(-2, -2, 2, 2, -2, -2, 2, 2)); // 16
        System.out.println(sol.computeArea(-2, -2, 2, 2, -3, -3, 3, -1)); // 24
        System.out.println(sol.computeArea(-2, -2, 2, 2, 1, -3, 3, 3)); // 24
    }

    public int computeArea(int x0, int y0, int x1, int y1, int p0, int q0, int p1, int q1) {
        // AREA = (x1-x0)*(y1-y0) + (p1-p0)*(q1-q0) - intersection
        int area = (x1 - x0) * (y1 - y0) + (p1 - p0) * (q1 - q0);
        // System.out.println("area = " + area);
        int w = 0;
        int h = 0;
        if (x0 <= p0 && p0 <= x1) w = p1 < x1 ? p1 - p0 : x1 - p0;
        else if (x0 <= p1 && p1 <= x1) w = p1 - x0;
        else if (x0 <= p0 && p1 <= x1) w = p1 - p0;
        else if (p0 <= x0 && x1 <= p1) w = x1 - x0;
        if (y0 <= q0 && q0 <= y1) h = q1 < y1 ? q1 - q0 : y1 - q0;
        else if (y0 <= q1 && q1 <= y1) h = q1 - y0;
        else if (y0 <= q0 && q1 <= y1) h = q1 - q0;
        else if (q0 <= y0 && y1 <= q1) h = y1 - y0;
        // System.out.println("w = " + w + ", h = " + h);
        return area - w * h;
    }
}
