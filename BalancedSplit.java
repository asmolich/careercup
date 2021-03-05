import java.util.Arrays;

/**
 * Facebook Interview Preparation
 * <p>
 * Balanced Split
 * <p>
 * Given an array of integers (which may include repeated integers), determine if there's a way to split the array into two subsequences A and B such that the sum of the integers in both arrays is the same, and all of the integers in A are strictly smaller than all of the integers in B.
 * <p>
 * Note: Strictly smaller denotes that every integer in A must be less than, and not equal to, every integer in B.
 * <p>
 * Signature
 * bool balancedSplitExists(int[] arr)
 * <p>
 * Input
 * All integers in array are in the range [0, 1,000,000,000].
 * <p>
 * Output
 * Return true if such a split is possible, and false otherwise.
 * <p>
 * Example 1
 * arr = [1, 5, 7, 1]
 * output = true
 * We can split the array into A = [1, 1, 5] and B = [7].
 * <p>
 * Example 2
 * arr = [12, 7, 6, 7, 6]
 * output = false
 * We can't split the array into A = [6, 6, 7] and B = [7, 12] since this doesn't satisfy the requirement that all integers in A are smaller than all integers in B.
 * <p>
 * Similar to https://leetcode.com/problems/tallest-billboard/
 */
public class BalancedSplit {
    public static void main(String[] args) {
        BalancedSplit sol = new BalancedSplit();
        System.out.println(sol.balancedSplitExists(new int[]{1, 5, 7, 1})); // true
        System.out.println(sol.balancedSplitExists(new int[]{12, 7, 6, 7, 6})); // false
        System.out.println(sol.balancedSplitExistsSort(new int[]{1, 5, 7, 1})); // true
        System.out.println(sol.balancedSplitExistsSort(new int[]{12, 7, 6, 7, 6})); // false
    }

    public boolean balancedSplitExistsSort(int[] arr) {
        if (arr == null || arr.length == 0) return false;

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if ((sum & 1) == 1) return false;

        Arrays.sort(arr);

        int lo = 0;
        int hi = arr.length - 1;
        int left = 0;
        int right = 0;
        while (lo <= hi) {
            if (lo != hi && arr[hi] == arr[lo]) return false;
            if (right < left) {
                right += arr[hi--];
            } else {
                left += arr[lo++];
            }
            // System.out.println(Map.of("hi", hi, "lo", lo, "left", left, "right", right, "arr", Arrays.toString(arr)));
        }
        return left == right;
    }

    public boolean balancedSplitExists(int[] arr) {
        if (arr == null || arr.length == 0) return false;

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if ((sum & 1) == 1) return false;

        return dfs(arr, 0,
                0, Integer.MIN_VALUE,
                0, Integer.MAX_VALUE, sum);
    }

    private boolean dfs(int[] arr, int index,
                        int sum1, int max1,
                        int sum2, int min2, int rest) {
        // System.out.println(Map.of("idx", index, "sum1", sum1, "sum2", sum2, "rest", rest));
        if (min2 <= max1) return false;
        if (Math.abs(sum1 - sum2) > rest) return false;

        if (index >= arr.length) {
            return sum1 == sum2;
        }
        int value = arr[index];
        return dfs(arr, index + 1,
                sum1 + value, Math.max(max1, value),
                sum2, min2, rest - value) ||
                dfs(arr, index + 1, sum1, max1,
                        sum2 + value, Math.min(min2, value), rest - value);
    }
}
