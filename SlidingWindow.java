import java.util.*;
public class SlidingWindow {
    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();
        //int[] data = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] data = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int wnd = 2;
        System.out.println("Max elements in \n" + Arrays.toString(data) + "\nwith sliding window of " + wnd + " is \n" + 
                Arrays.toString(sw.findMaxBruteForce(data, wnd)) + "");
    }

    public int findMax(int[] a) {
        return findMax(a, 0, a.length - 1);
    }

	public int findMax(int[] a, int lo, int hi) {
        if (a == null || lo > hi || hi >= a.length) return -1;
 
        int max = lo;
        for(int i = lo + 1; i <= hi; i++) {
            if (a[i] > a[max]) max = i;
        }
        return max;
	}

    public int[] findMaxBruteForce(int[] a, int w) {
        if (a == null || a.length == 0 || w <= 1) return a;
        
        if (w >= a.length) {
            // just find max
            int idx = findMax(a);
            return idx >= 0 ? new int[] {a[idx]} : a;
        }

        int len = a.length - w + 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int idx = findMax(a, i, i + w - 1);
            if (idx >= 0) {
                res[i] = a[idx];
            }
        }
        return res;
    }

    public int[] findMax(int[] a, int w) {
        if (a == null || a.length == 0 || w <= 1) return a;
        
        if (w >= a.length) {
            // just find max
            int idx = findMax(a);
            return idx >= 0 ? new int[] {a[idx]} : a;
        }

        int len = a.length - w + 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int idx = findMax(a, i, i + w - 1);
            if (idx >= 0) {
                res[i] = a[idx];
            }
        }
        return res;
    }
}
