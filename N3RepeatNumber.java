import java.util.Arrays;

public class N3RepeatNumber {
    public static void main(String[] args) {
        N3RepeatNumber rn = new N3RepeatNumber();
        int[] data = new int[]
            //{2,3,4,5,6,7,1,1,1};
            {1, 2, 3, 1, 2, 3, 4, 1};
        System.out.println(Arrays.toString(data));
        System.out.println(rn.repeatedNumber(data));
    }

    static class Bucket {
        int val = -1;
        int count = 0;
    }

    /**
     * You’re given a read only array of n integers. Find out if any integer occurs more than n/3 times in the array in linear time and constant additional space.
     *
     * If so, return the integer. If not, return -1.
     *
     * If there are multiple solutions, return any one.
     */
    public int repeatedNumber(int[] a) {
        if (a == null || a.length < 1) return -1;
        if (a.length < 3) return a[0];

        int n = a.length;
        int n3 = n / 3;

        Bucket b1 = new Bucket();
        Bucket b2 = new Bucket();
        for (int i = 0; i < n; i++) {
            int ai = a[i];

            if (b1.val == -1) {
                b1.val = ai;
                b1.count = 1;
            } else if (b1.val == ai) {
                b1.count++;
                if (b1.count > n3) return ai;
            } else if (b2.val == -1) {
                b2.val = ai;
                b2.count = 1;
            } else if (b2.val == ai) {
                b2.count++;
                if (b2.count > n3) return ai;
            } else if (b1.count <= 0) {
                b1.count = 1;
                b1.val = ai;
            } else if (b2.count <= 0) {
                b2.count = 1;
                b2.val = ai;
            } else {
                b1.count--;
                b2.count--;
            }
            if (n3 < 11) {
                System.out.println("n3=" + n3 + ", b1.v=" + b1.val + ", b1.c=" + b1.count + ", b2.v=" + b2.val + ", b2.c=" + b2.count);
            }
        }

        if (b1.count > 0 || b2.count > 0) {
            b1.count = 0;
            b2.count = 0;
            for (int i = 0; i < n; i++) {
                int ai = a[i];
                if (b1.val == ai) {
                    b1.count++;
                    if (b1.count > n3) return ai;
                }
                if (b2.val == ai) {
                    b2.count++;
                    if (b2.count > n3) return ai;
                }
            }
        }

        return -1;
    }
}
