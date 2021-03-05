/**
 * LeetCode
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 * #Medium #TwoPointer
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater sol = new ContainerWithMostWater();
        System.out.println(sol.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})); // 49
        System.out.println(sol.maxArea(new int[]{1, 1})); // 1
        System.out.println(sol.maxArea(new int[]{4, 3, 2, 1, 4})); // 16
        System.out.println(sol.maxArea(new int[]{1, 2, 1})); // 2
    }

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;

        int n = height.length;
        int lo = 0;
        int hi = n - 1;
        int max = 0;
        while (lo < hi) {
            max = Math.max(max, Math.min(height[lo], height[hi]) * (hi - lo));
            if (height[lo] < height[hi]) lo++;
            else hi--;
        }
        return max;
    }
}
