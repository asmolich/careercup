import java.util.Arrays;

public class BinarySearchInRotatedArray {
    public static void main(String[] args) {
        BinarySearchInRotatedArray bs = new BinarySearchInRotatedArray();
        System.out.println(bs.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
        System.out.println(bs.search(new int[]{101, 103, 106, 109, 158, 164, 182, 187, 202, 205, 2, 3, 32, 57, 69, 74, 81, 99, 100}, 202));
        System.out.println(bs.search(new int[]{3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 4));
        System.out.println(bs.search(new int[]{3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 3));
        System.out.println(bs.search(new int[]{1, 7, 67, 133, 178}, 1));
    }

    private int search(final int[] a, int b) {
        if (a == null || a.length == 0) return -1;

        System.out.println("a = " + Arrays.toString(a) + "\nb = " + b);
        int n = a.length;
        int pivot = searchPivot(a);
        int lo = pivot;
        int hi = pivot + n - 1;
        System.out.println("pivot = " + pivot);

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
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
            int mid = lo + (hi - lo) / 2;

            if (a[lo] > a[hi]) {
                if (a[mid] >= a[lo]) lo = mid + 1;
                else hi = mid - 1;
            }
            if (a[mid] == a[hi] && a[lo] > a[mid]) {
                return mid; // pivot
            }
            if (a[lo] == a[hi]) {
                hi--;
                continue;
            }

//            if (a[mid] == a[hi] && a[mid] == a[lo]) {
//                while (lo < hi && a[lo] == a[hi]) {
//                    lo++;
//                    hi--;
//                    mid = lo + (hi - lo) / 2;
//                    int midVal = a[mid];
//                    if (a[lo] < midVal) {
//                        return lo;
//                    } else if (a[hi] > midVal) {
//                        return hi;
//                    }
//                }
//
//                mid = lo + (hi - lo) / 2;
//            }


            if (mid < a.length - 1 && a[mid] > a[mid + 1]) return mid + 1;
            else if (mid > 0 && a[mid] < a[mid - 1]) return mid;
            else if (a[mid] > a[hi]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return 0;
    }
}
