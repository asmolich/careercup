import java.util.Arrays;

/**
 * LeetCode
 * 327. Count of Range Sum
 * https://leetcode.com/problems/count-of-range-sum/
 * #Hard
 */
public class CountOfRangeSum {
    public static void main(String[] args) {
        CountOfRangeSum sol = new CountOfRangeSum();
        System.out.println(sol.countRangeSum(new int[]{-2, 5, -1}, -2, 2)); // 3
        // Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
        System.out.println(sol.countRangeSum(new int[]{-2147483647, 0, -2147483647, 2147483647}, -564, 3864)); // 3
    }

    private static class BIT {
        private final int[] arr;

        public BIT(int n) {
            arr = new int[n + 1];
        }

        void update(int i) {
            if (i == 0) throw new IllegalArgumentException("Index is one-based");
            while (i < arr.length) {
                arr[i]++; // count
                i += i & -i;
            }
        }

        private int get(int i) {
            if (i == 0) throw new IllegalArgumentException("Index is one-based");
            int res = 0;
            while (i > 0) {
                res += arr[i];
                i -= i & -i;
            }
            return res;
        }
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        // Return number of range sums that lie in [lower, upper] inclusive.
        // prefix sums
        // merge sort

        int n = nums.length;
        long[] prefixSums = new long[n + 1];
        prefixSums[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        // System.out.println("prefixSums = " + Arrays.toString(prefixSums));

        return mergePrefixSums(prefixSums, 0, n, new long[n + 1], lower, upper);
    }

    // merge sort
    private int mergePrefixSums(long[] prefixSums, int lo, int hi, long[] aux, int lower, int upper) {
        if (lo >= hi) return 0;

        int mid = (lo + hi) >>> 1;
        int count = 0;
        count += mergePrefixSums(prefixSums, lo, mid, aux, lower, upper);
        count += mergePrefixSums(prefixSums, mid + 1, hi, aux, lower, upper);

        // copy to aux
        if (hi + 1 - lo >= 0) System.arraycopy(prefixSums, lo, aux, lo, hi + 1 - lo);

        // count
        int j1 = mid + 1, j2 = mid + 1; //j1, j2 must be in [mid+1, hi]
        for (int i = lo; i <= mid; i++) {
            // find min j1 such that prefixSums[j1] - prefixSums[i] >= lower
            while (j1 <= hi && prefixSums[j1] - prefixSums[i] < lower) j1++;
            if (j1 > hi) break;

            // find max j2 such that prefixSums[j2] - prefixSums[i] <= upper
            while (j2 <= hi && prefixSums[j2] - prefixSums[i] <= upper) j2++;
            if (j2 == mid + 1) continue;
            if (j2 > hi) j2 = hi;
            if (prefixSums[j2] - prefixSums[i] > upper) j2--;

            if (j2 >= j1) count += j2 - j1 + 1;
        }
        // merge
        int p1 = lo, p2 = mid + 1;
        while (p1 <= mid && p2 <= hi) prefixSums[lo++] = (aux[p1] < aux[p2]) ? aux[p1++] : aux[p2++];
        while (p1 <= mid) prefixSums[lo++] = aux[p1++];
        while (p2 <= hi) prefixSums[lo++] = aux[p2++];
        return count;
    }

    public int countRangeSumBIT(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        // Return number of range sums that lie in [lower, upper] inclusive.
        // prefix sums
        // BIT for log searches

        int n = nums.length;
        int[] prefixSums = new int[n + 1];
        prefixSums[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        System.out.println("prefixSums = " + Arrays.toString(prefixSums));

        BIT bit = new BIT(n + 1);
        int count = 0;
        for (var sum : prefixSums) {

            System.out.println("sum - lower = " + (sum - lower));
            System.out.println("upper - sum = " + (upper - sum));
            int lo = bit.get(upperBound(prefixSums, sum - lower));
            int hi = bit.get(lowerBound(prefixSums, upper - sum));

            count += lo - hi;

            bit.update(upperBound(prefixSums, sum));
        }
        return count;
    }

    private int lowerBound(int[] a, int key) {
        int l = -1, r = a.length;
        while (l + 1 < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= key) r = m;
            else l = m;
        }
        return r;
    }

    private int upperBound(int[] a, int key) {
        int l = -1, r = a.length;
        while (l + 1 < r) {
            int m = (l + r) >>> 1;
            if (a[m] <= key) l = m;
            else r = m;
        }
        return l + 1;
    }

    // O(N^2) - prefix sums
    @SuppressWarnings("unused")
    public int countRangeSumON2(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] prefixSums = new int[n + 1];
        prefixSums[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
        }
        int count = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = prefixSums[j] - prefixSums[i];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }

        return count;
    }
}
