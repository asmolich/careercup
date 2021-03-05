/**
 * LeetCode
 * 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * #Medium #BinarySearch #Rotated
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        SearchInRotatedSortedArray bs = new SearchInRotatedSortedArray();
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4)); // 0
        System.out.println(bs.search(new int[]{101, 103, 106, 109, 158, 164, 182, 187, 202, 205, 2, 3, 32, 57, 69, 74, 81, 99, 100}, 202)); // 8
        System.out.println(bs.search(new int[]{3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 4)); // 1
        System.out.println(bs.search(new int[]{3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 3)); // ?
        System.out.println(bs.search(new int[]{1, 7, 67, 133, 178}, 1)); // 0
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
        System.out.println(bs.search(new int[]{1}, 0)); // -1
        System.out.println(bs.search(new int[]{3, 1}, 1)); // 1
    }

    private int search(final int[] a, int b) {
        if (a == null || a.length == 0) return -1;

        int n = a.length;
        int pivot = searchPivot(a);
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

    private int searchPivot(final int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
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
