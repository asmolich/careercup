/**
 * LeetCode
 * 29. Divide Two Integers. https://leetcode.com/problems/divide-two-integers/
 */
class Division {

    public static void main(String[] args) {
        System.out.println(divide(Integer.MAX_VALUE, Integer.MAX_VALUE));
        System.out.println(divide(Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(divide(Integer.MIN_VALUE, Integer.MIN_VALUE));

        System.out.println(divide(2147483647, 2));
        System.out.println(divide(-2147483647, -2));
        System.out.println(divide(2147483647, 1));
    }

    private static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) return Integer.MIN_VALUE;
            if (divisor == -1) return Integer.MAX_VALUE;
            if (divisor == Integer.MIN_VALUE) return 1;
            if (divisor == Integer.MAX_VALUE) return -1;
        }
        if (dividend == divisor) return 1;
        if (divisor == Integer.MIN_VALUE) return 0;

        boolean pos = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

        dividend = dividend < 0 ? -dividend : dividend;
        int result = 0;
        if (dividend > 0) result += dividePositive(dividend, divisor < 0 ? -divisor : divisor);
        else result += divideNegative(dividend, divisor < 0 ? divisor : -divisor);

        return pos ? result : -result;
    }

    private static int dividePositive(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        System.out.println("dividePositive call, dividend = " + dividend + ", divisor = " + divisor);
        int result = 0;
        int tempDivisor;
        int count;

        while (dividend >= divisor) {
            System.out.println("dividend =" + dividend + ", divisor = " + divisor);
            tempDivisor = divisor;
            count = 0;
            while (dividend > tempDivisor << 1 && tempDivisor << 1 > 0) {
                System.out.println("dividend =" + dividend + ", tempDivisor = " + tempDivisor);
                tempDivisor <<= 1;
                count += 1;
                if (count >= 31) return Integer.MAX_VALUE;
            }
            System.out.println("dividend =" + dividend + ", tempDivisor = " + tempDivisor);
            dividend -= tempDivisor;
            System.out.println("dividend =" + dividend);
            result += 1 << count;
            System.out.println("count =" + count + ", result = " + result);
        }
        return result;
    }

    private static int divideNegative(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        System.out.println("divideNegative call, dividend = " + dividend + ", divisor = " + divisor);
        int result = 0;
        int tempDivisor;
        int count;

        while (dividend <= divisor) {
            tempDivisor = divisor;
            count = 0;
            while (dividend < tempDivisor << 1 && tempDivisor << 1 < 0) {
                tempDivisor <<= 1;
                count += 1;
                if (count >= 31) return Integer.MAX_VALUE;
            }
            dividend -= tempDivisor;
            result += 1 << count;
        }
        return result;
    }
}
