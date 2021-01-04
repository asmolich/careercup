/**
 * InterviewBit
 * Array 3 Pointers
 * https://www.interviewbit.com/problems/array-3-pointers/
 * <p>
 * You are given 3 arrays A, B and C. All 3 of the arrays are sorted.
 * <p>
 * Find i, j, k such that: max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
 * Return the minimum
 */
public class Array3Pointers {
    public static void main(String[] args) {
        Array3Pointers a3p = new Array3Pointers();
        int[] x = {1, 4, 10};
        int[] y = {2, 15};
        int[] z = {10, 12};

        System.out.println(a3p.minimize(x, y, z));
    }

    private int minimize(int[] a, int[] b, int[] c) {
        if (a == null || a.length == 0 || b == null || b.length == 0 || c == null || c.length == 0) return -1;

        int i = 0;
        int j = 0;
        int k = 0;
        int globalMin = Integer.MAX_VALUE;
        while (i < a.length && j < b.length && k < c.length) {
            int min = Math.min(a[i], Math.min(b[j], c[k]));
            int max = Math.max(a[i], Math.max(b[j], c[k]));

            //System.out.format("a[%s] = %2s, b[%s] = %2s, c[%s] = %2s, max = %2s %n", i, a[i], j, b[j], k, c[k], max);

            int diff = max - min;
            if (globalMin > diff) globalMin = diff;

            if (min == a[i]) i++;
            else if (min == b[j]) j++;
            else k++;
        }

        return globalMin;
    }
}
