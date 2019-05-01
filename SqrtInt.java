public class SqrtInt {
    public static void main(String[] args) {
        SqrtInt sqrt = new SqrtInt();
        System.out.println(sqrt.sqrt(930675566));
    }

    public int sqrt(int a) {
        if (a <= 1) return a;

        int lo = 1;
        int hi = a - 1;
        long closest = a;
        int res = -1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);

            long sq = (long) mid * mid;
            if (sq == a) return mid;
            else {
                if (sq > a) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;

                    long diff = a - sq;
                    if (closest > diff) {
                        closest = diff;
                        res = mid;
                    }
                }
            }
        }
        return res;
    }
}
