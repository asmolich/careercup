/**
 * LeetCode 4. Median of Two Sorted Arrays.
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * #Hard #BinarySearch
 * #Incomplete
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();
        int[] a = new int[]{1, 2, 3, 4, 5, 6};
        int[] b = new int[]{1, 2, 3, 7, 8, 9, 10};
        System.out.println("1. median = " + sol.medianNaive(a, b));
        System.out.println("2. median = " + sol.median(a, b));
    }

    /**
     * Effectively a median of sorted array that was made by merging arrays a and b into one.
     * Expected run time is O(log(m+n))
     */
    @SuppressWarnings("Duplicates")
    private double median(int[] a, int[] b) {
        if (a == null || b == null) return -1;
        if (a.length == 0) {
            int n = b.length;
            if (n % 2 == 0) {
                return (b[n / 2] + b[n / 2 - 1]) / 2.0d;
            } else return b[n / 2];
        }
        if (b.length == 0) {
            int n = a.length;
            if (n % 2 == 0) {
                return (a[n / 2] + a[n / 2 - 1]) / 2.0d;
            } else return a[n / 2];
        }

        if (a[a.length - 1] <= b[0]) {
            int n = a.length + b.length;
            if (n % 2 == 0) {
                int x;
                int y;
                if (n / 2 - 1 < a.length) {
                    x = a[n / 2 - 1];
                } else {
                    x = b[n / 2 - 1 - a.length];
                }
                if (n / 2 < a.length) {
                    y = a[n / 2];
                } else {
                    y = b[n / 2 - a.length];
                }
                return (x + y) / 2.0d;
            } else {
                if (n / 2 < a.length) {
                    return a[n / 2];
                } else {
                    return b[n / 2 - a.length];
                }
            }
        }
        if (b[b.length - 1] <= a[0]) {
            int n = a.length + b.length;
            if (n % 2 == 0) {
                int x;
                int y;
                if (n / 2 - 1 < a.length) {
                    x = b[n / 2 - 1];
                } else {
                    x = a[n / 2 - 1 - b.length];
                }
                if (n / 2 < b.length) {
                    y = b[n / 2];
                } else {
                    y = a[n / 2 - b.length];
                }
                return (x + y) / 2.0d;
            } else {
                if (n / 2 < b.length) {
                    return b[n / 2];
                } else {
                    return a[n / 2 - b.length];
                }
            }
        }

        // General case
        int loA = 0;
        int loB = 0;
        int hiA = a.length - 1;
        int hiB = b.length - 1;
        int n = a.length + b.length;
        while (loA <= hiA || loB <= hiB) {
            if (loA == hiA && loB == hiB) {
                // exit condition
                break;
            }
            int midA = loA + (hiA - loA) / 2;
            int midB = loB + (hiB - loB) / 2;
            if (loA <= hiA) {

            }
            if (loB <= hiB) {
            }
        }

        return 0;
    }

    @SuppressWarnings("Duplicates")
    // O(m+n) time, O(m+n) space
    private double medianNaive(int[] a, int[] b) {
        if (a == null || b == null) return -1;
        if (a.length == 0) {
            int n = b.length;
            if (n % 2 == 0) {
                return (b[n / 2] + b[n / 2 - 1]) / 2.0d;
            } else return b[n / 2];
        }
        if (b.length == 0) {
            int n = a.length;
            if (n % 2 == 0) {
                return (a[n / 2] + a[n / 2 - 1]) / 2.0d;
            } else return a[n / 2];
        }

        int n = a.length + b.length;
        int[] c = new int[n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < n) {
            if (i >= a.length) {
                c[k++] = b[j++];
            } else if (j >= b.length) {
                c[k++] = a[i++];
            } else if (a[i] <= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
            if (k > n / 2) {
                if (n % 2 == 0) {
                    return (c[n / 2] + c[n / 2 - 1]) / 2.0d;
                } else return c[n / 2];
            }
        }
        return -1;
    }

    private int binarySearch(int[] a, int k) {
        if (a == null) return -1;

        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == k) {
                return mid;
            } else if (a[mid] < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}

