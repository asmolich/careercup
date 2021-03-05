/**
 * LeetCode
 * 413. Arithmetic Slices
 * https://leetcode.com/problems/arithmetic-slices/
 * #Medium #DP
 */
public class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices sol = new ArithmeticSlices();
        System.out.println(sol.numberOfArithmeticSlices(new int[]{1, 2, 3, 4})); // 3
    }

    public int numberOfArithmeticSlices(int[] a) {
        if (a == null || a.length == 0) return 0;

        int n = a.length;
        int total = 0;
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (a[i] - a[i - 1] == a[i - 1] - a[i - 2]) {
                count++;
                total += count;
            } else {
                count = 0;
            }
        }
        return total;
    }
}
