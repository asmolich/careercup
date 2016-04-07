import java.util.*;
public class Inversions {
	public static void main(String[] args) {
        int[] data = new int[]{84, 2, 37, 3, 67, 82, 19, 97, 91, 63, 27, 6, 13, 90, 63, 89, 100, 60, 47, 96, 54, 26, 64, 50, 71, 16, 6, 40, 84, 93, 67, 85, 16, 22, 60};
        System.out.println(Arrays.toString(data));

        int[] d = data.clone();
        Inversions in = new Inversions();
        System.out.println("Inversions number: " + in.countInversionsBruteForce(d));
        System.out.println("Count inversions: " + in.countInversions(data));
    
        Arrays.sort(d);
        //Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(d));
	}

    class Pair{
        int a = 0;
        int b = 0;
        @Override
        public String toString() {
            return "["+a+", "+b+"]";
        }
    }
    public int countInversionsBruteForce(int[] a) {
        if (a == null || a.length <= 1) return 0;
        return countInversionsBruteForce(a, 0, a.length - 1);
    }
    public int countInversionsBruteForce(int[] a, int lo, int hi) {
        if (a == null || a.length <= 0 || hi <= lo) return 0;

        int count = 0;
        int n = hi - lo + 1;
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    count++;
                    Pair p = new Pair();
                    p.a = a[i];
                    p.b = a[j];
                    pairs.add(p);
                }
            }
        }
        //System.out.println("Inversions: " + pairs);
        return count;
    }

    public int countInversions(int[] a) {
        if (a == null || a.length <= 1) return 0;
 
        int[] aux = new int[a.length];

        return countInversions0(a, aux, 0, a.length - 1);
    }

    private int countInversions0(int[] a, int[] aux, int i, int j) {
        if (j <= i) return 0;

        int mid = i + (j - i) / 2;
        int x = countInversions0(a, aux, i, mid);
        int y = countInversions0(a, aux, mid + 1, j);
        int z = countInversionsMerge0(a, aux, i, mid, j);
        return x + y + z;
    }
    private int countInversionsMerge0(int[] a, int[] aux, int i, int mid, int j) {
        int count = 0;

        int lo = i, hi = mid + 1;
        //System.out.println("merge. i = " + i + ", mid = " + mid + ",  j = " + j);
        for (int k = i; k <= j; k++) {
        //System.out.println("merge. lo = " + lo + ", hi = " + hi + ",  k = " + k);
            if (lo > mid) {
                aux[k] = a[hi++];
            }
            else if (hi > j) {
                aux[k] = a[lo++];
            }
            else if (a[lo] <= a[hi]) {
                aux[k] = a[lo++];
            }
            else {
                aux[k] = a[hi++];
                count += mid - lo + 1;
            }
        }

        for (int k = i; k <= j; k++) {
            a[k] = aux[k];
        }

        //int[] t = new int[j-i+1];
        //System.arraycopy(a, i, t, 0, j-i+1);
        //System.out.println(Arrays.toString(t) +" => "+ count);

        return count;
    }
}

