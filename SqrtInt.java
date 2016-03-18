import java.util.*;
public class SqrtInt {
    public static void main(String[] args) {
        SqrtInt sqrt = new SqrtInt();
        System.out.println(sqrt.sqrt(120));
    }

    public int sqrt(int a) {
        if (a <= 1) return a;

        int lo = 1;
        int hi = a - 1;
        int closest = a;
        int res = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long sq = mid * mid;
            if (sq == a) return mid;
            else {
                long diff = Math.abs(sq - a);
                if (closest > diff) {
                    closest = (int)diff;
                    res = mid;
                }
                if (sq > a) {
                    hi = mid - 1;
                }
                else {
                    lo = mid + 1;
                }
            }
        }
        return res;
    }
}
