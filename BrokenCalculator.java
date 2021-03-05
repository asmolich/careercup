/**
 * LeetCode
 * 991. Broken Calculator
 * https://leetcode.com/problems/broken-calculator/
 * #Medium #Greedy
 */
public class BrokenCalculator {
    public static void main(String[] args) {
        BrokenCalculator sol = new BrokenCalculator();
        System.out.println(sol.brokenCalc(2, 3)); // 2
        System.out.println(sol.brokenCalc(5, 8)); // 2
        System.out.println(sol.brokenCalc(3, 10)); // 3
        System.out.println(sol.brokenCalc(1024, 1)); // 1023
    }

    public int brokenCalc(int x, int y) {
        int count = 0;
        while (y > x) {
            count++;
            if (y % 2 == 1) y++;
            else y /= 2;
        }
        return count + x - y;
    }
}
