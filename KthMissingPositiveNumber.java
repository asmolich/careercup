/**
 * LeetCode
 * 1539. Kth Missing Positive Number
 * https://leetcode.com/problems/kth-missing-positive-number/
 * #Easy
 */
public class KthMissingPositiveNumber {
    public static void main(String[] args) {
        KthMissingPositiveNumber sol = new KthMissingPositiveNumber();
        System.out.println(sol.findKthPositive(new int[]{2, 3, 4, 7, 11}, 5)); // 9
        System.out.println(sol.findKthPositive(new int[]{1, 2, 3, 4}, 2)); // 6
    }

    public int findKthPositive(int[] arr, int k) {
        if (arr == null || arr.length == 0) return k;

        int n = arr.length;
        int expected = 1;
        int mismatch = 0;
        int i = 0;
        while (i < n) {
            // System.out.println("i = " + i + ", expected = " + expected + ", mismatch = " + mismatch);
            if (arr[i] != expected) {
                mismatch++;
                if (mismatch == k) {
                    return expected;
                }
                i--;
            }
            expected++;
            i++;
        }
        return arr[n - 1] + k - mismatch;
    }
}
