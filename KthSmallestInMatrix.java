import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/
 * <pre>
 * Given a matrix A.
 * Property: A[i][j] <= A[i+1][j]
 *           A[i][j] <= A[i][j+1]
 * Find Kth smallest element.
 *
 * Time:  O(k log(max(m,n)))
 * Space: O(max(m,n))
 * </pre>
 */
@SuppressWarnings("Duplicates")
public class KthSmallestInMatrix {
    public static void main(String[] args) {
        KthSmallestInMatrix dp = new KthSmallestInMatrix();
        int[][] matrix = new int[][]{
                {1, 4, 5, 10, 100},
                {2, 6, 7, 12, 101},
                {3, 8, 9, 13, 102},
                {110, 111, 112, 113, 120}
        };
        System.out.println("Kth smallest in matrix:");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        System.out.println("1st: " + dp.findKthSmallestInMatrix(matrix, 0));
        System.out.println("1st: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 0));
        System.out.println("2nd: " + dp.findKthSmallestInMatrix(matrix, 1));
        System.out.println("2nd: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 1));
        System.out.println("3rd: " + dp.findKthSmallestInMatrix(matrix, 2));
        System.out.println("3rd: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 2));
        System.out.println("4th: " + dp.findKthSmallestInMatrix(matrix, 3));
        System.out.println("4th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 3));
        System.out.println("5th: " + dp.findKthSmallestInMatrix(matrix, 4));
        System.out.println("5th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 4));
        System.out.println("6th: " + dp.findKthSmallestInMatrix(matrix, 5));
        System.out.println("6th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 5));
        System.out.println("7th: " + dp.findKthSmallestInMatrix(matrix, 6));
        System.out.println("7th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 6));
        System.out.println("8th: " + dp.findKthSmallestInMatrix(matrix, 7));
        System.out.println("8th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 7));
        System.out.println("9th: " + dp.findKthSmallestInMatrix(matrix, 8));
        System.out.println("9th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 8));
        System.out.println("10th: " + dp.findKthSmallestInMatrix(matrix, 9));
        System.out.println("10th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 9));
        System.out.println("13th: " + dp.findKthSmallestInMatrix(matrix, 12));
        System.out.println("13th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 12));
        System.out.println("100th: " + dp.findKthSmallestInMatrix(matrix, 99));
        System.out.println("100th: " + dp.findKthSmallestInMatrixBinarySearch(matrix, 99));
    }

    public int findKthSmallestInMatrixBinarySearch(int[][] a, /* 0-based */ int k) {
        if (a == null || k < 0) return -1;
        if (k == 0) return a[0][0];
        if (a.length < 1 || a[0].length < 1 || k >= a.length * a[0].length) return -1;
        int n = a.length;
        int m = a[0].length;
        if (k == n * m - 1) return a[n - 1][m - 1];

        int midRow = n / 2;
        int midCol = m / 2;
        int midElem = a[midRow][midCol];

        int[] mid = new int[n];
        int[] from = new int[n];
        int[] to = new int[n];
        for (int i = 0; i < n; i++) {
            to[i] = n - 1;
        }

        while (k >= 0) {
            int sum = midCol;
            int minMidRow = midRow;
            int maxMidRow = midRow;
            for (int i = 0; i < n; i++) {
                if (i == midRow) continue;
                mid[i] = Math.abs(Arrays.binarySearch(a[i], from[i], to[i], midElem));
                minMidRow = Math.min(minMidRow, mid[i]);
                maxMidRow = Math.max(maxMidRow, mid[i]);
                sum += mid[i];
            }
            if (sum == k) return midElem;
            if (sum > k) {
                System.arraycopy(mid, 0, to, 0, n);
                midRow = maxMidRow;
            } else {
                System.arraycopy(mid, 0, from, 0, n);
                k -= sum;
                midRow = minMidRow;
            }
        }

        return -1;
    }


    public int findKthSmallestInMatrix(int[][] a, /* 0-based */ int k) {
        if (a == null || k < 0) return -1;

        if (k == 0) return a[0][0];
        if (a.length < 1 || a[0].length < 1 || k >= a.length * a[0].length) return -1;

        int n = a.length;
        int m = a[0].length;
        if (k == n * m - 1) return a[n - 1][m - 1];

        boolean rows = n > m;
        int capacity = Math.max(m, n);
        PriorityQueue<Elem> border = new PriorityQueue<>(capacity);
        Map<Integer, Elem> map = new HashMap<>(capacity);
        Elem el = new Elem(0, 0, a[0][0]);
        border.add(el);
        map.put(0, el);
        while (!border.isEmpty() && k >= 0) {
            //System.out.println(border);
            Elem min = border.poll();
            if (k == 0) return min.val;
            map.remove(rows ? min.i : min.j);
            k--;

            if (min.i < n - 1) {
                el = new Elem(min.i + 1, min.j, a[min.i + 1][min.j]);
                Integer key = rows ? el.i : el.j;
                if (!map.containsKey(key)) {
                    map.put(key, el);
                    border.add(el);
                }
            }
            if (min.j < m - 1) {
                el = new Elem(min.i, min.j + 1, a[min.i][min.j + 1]);
                Integer key = rows ? el.i : el.j;
                if (!map.containsKey(key)) {
                    border.add(el);
                    map.put(key, el);
                }
            }
        }
        return -1;
    }

    static class Elem implements Comparable<Elem> {
        final int i, j, val;

        Elem(int x, int y, int v) {
            i = x;
            j = y;
            val = v;
        }

        @Override
        public int compareTo(Elem el) {
            return Integer.compare(val, el.val);
        }

        @Override
        public String toString() {
            return "[" + i + ", " + j + ", " + val + "]";
        }

        @Override
        public int hashCode() {
            return 31 * val + 13 * i + 17 * j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Elem)) return false;
            Elem el = (Elem) o;
            return (i == el.i) && (j == el.j) && (val == el.val);
        }
    }
}
