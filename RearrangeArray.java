import java.util.*;
public class RearrangeArray {
    public static void main(String[] args) {
        RearrangeArray ra = new RearrangeArray();
        int[] data = new int[]{4, 0, 2, 1, 3};
        System.out.println("Input:   " + Arrays.toString(data));
        System.out.println("Result:  " + Arrays.toString(ra.rearrange(data)));
        ra.rearrange2(data);
        System.out.println("Result2: " + Arrays.toString(data));
    }

    public int[] rearrange(int[] a) {
        if (a == null) return null;

        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[a[i]];
        }
        return b;
    }

    public void rearrange2(int[] a) {
        if (a == null) return;

        int n = a.length;
        for (int i = 0; i < n; i++) {
            a[i] = a[i] + a[a[i]] % n * n;
        }

        for (int i = 0; i < n; i++) {
            a[i] /= n;
        }
    }
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
