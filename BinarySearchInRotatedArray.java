import java.util.*;
public class BinarySearchInRotatedArray {
    public static void main(String[] args) {
        BinarySearchInRotatedArray bs = new BinarySearchInRotatedArray();
        //System.out.println(bs.search(Arrays.asList(4, 5, 6, 7, 0, 1, 2), 4));
        //System.out.println(bs.search(Arrays.asList(101, 103, 106, 109, 158, 164, 182, 187, 202, 205, 2, 3, 32, 57, 69, 74, 81, 99, 100), 202));
        System.out.println(bs.search(Arrays.asList(3, 4, 5, 6, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3), 4));
        //System.out.println(bs.search(Arrays.asList(1, 7, 67, 133, 178), 1));
    }
    public int search(final List<Integer> a, int b) {
        if (a == null || a.isEmpty()) return -1;
        
        System.out.println(a);
        int n = a.size();
        int pivot = searchPivot(a);
        int lo = pivot;
        int hi = pivot + n - 1;
        System.out.println("pivot = " + pivot);

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int midVal = a.get(mid % n);
            if (midVal == b) return mid % n;
            else if (midVal < b) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }
	
    private int searchPivot(final List<Integer> a) {
        int lo = 0;
        int hi = a.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a.get(mid) == a.get(hi) && a.get(mid) == a.get(lo)) {
                while (lo < hi && a.get(lo) == a.get(hi)) {
                    lo++; hi--;
                    mid = lo + (hi - lo) / 2;
                    int midVal = a.get(mid);
                    if (a.get(lo) < midVal) {
                        return lo;
                    }
                    else if (a.get(hi) > midVal) {
                        return hi;
                    }
                }

                mid = lo + (hi - lo) / 2;
            }

            if (mid < a.size() - 1 && a.get(mid) > a.get(mid + 1)) return mid + 1;
            else if (mid > 0 && a.get(mid) < a.get(mid - 1)) return mid;
            else if (a.get(mid) > a.get(hi)) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return 0;
    }
}
