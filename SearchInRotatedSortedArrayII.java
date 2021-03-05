/**
 * LeetCode
 * 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * #Medium #BinarySearch #Rotated
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
        SearchInRotatedSortedArrayII bs = new SearchInRotatedSortedArrayII();
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4)); // 0
        System.out.println(bs.search(new int[]{101, 103, 106, 109, 158, 164, 182, 187, 202, 205, 2, 3, 32, 57, 69, 74, 81, 99, 100}, 202)); // 8
        System.out.println(bs.search(new int[]{3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 4)); // 1
        System.out.println(bs.search(new int[]{3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 3)); // ?
        System.out.println(bs.search(new int[]{1, 7, 67, 133, 178}, 1)); // 0
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
        System.out.println(bs.search(new int[]{1}, 0)); // -1
        System.out.println(bs.search(new int[]{3, 1}, 1)); // 1
        System.out.println(bs.search(new int[]{8, 8, 8, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}, 7)); // 3
        System.out.println(bs.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 2)); // 13
    }

    private int search(int[] a, int b) {
        if (a == null || a.length == 0) return -1;

        int n = a.length;
        int pivot = searchPivot(a, 0, n - 1);
        int lo = pivot;
        int hi = pivot + n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = a[mid % n];
            if (midVal == b) return mid % n;
            else if (midVal < b) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    private int searchPivot(final int[] a, int lo, int hi) {
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] == a[lo] && a[mid] == a[hi]) {
                for (int i = lo + 1; i <= hi; i++) {
                    if (a[i - 1] > a[i]) return i;
                }
            }
            if (mid < a.length - 1 && a[mid] > a[mid + 1]) {
                return mid + 1;
            } else if (mid > 0 && a[mid] < a[mid - 1]) {
                return mid;
            } else if (a[mid] > a[hi]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return 0;
    }
}
