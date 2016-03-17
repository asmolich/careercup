import java.util.*;

public class DistanceMaximizingProblem {
    public static void main(String[] args) {
        DistanceMaximizingProblem dmp = new DistanceMaximizingProblem();
        System.out.println(dmp.distMax(new int[]{4,3,5,2,1,3,2,3}));
    }

	public int distMax(int[] a) {
        if (a == null || a.length < 2) return 0;
        if (a.length == 2) return 1; 

        int[] loMin = new int[a.length];
        int[] hiMax = new int[a.length];

        // Possible `i` indexes
        loMin[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            loMin[i] = Math.min(loMin[i - 1], a[i]);
        }
        
        // Possible `j` indexes
        hiMax[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            hiMax[i] = Math.max(hiMax[i + 1], a[i]);
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(loMin));
        System.out.println(Arrays.toString(hiMax));

        int maxDiff = 0;
        // Scan both arrays from left to right to find max (j - i)
        for (int i = 0, j = 0; i < a.length && j < a.length;) {
            if (loMin[i] <= hiMax[j]) {
                if (maxDiff < j - i) maxDiff = j - i;
                j++;
            }
            else {
                i++;
            }
        }
        return maxDiff;
    }
}
