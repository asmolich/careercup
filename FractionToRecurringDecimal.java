import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 166. Fraction to Recurring Decimal
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * #Medium #HashTable
 * #Incomplete
 */
public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        FractionToRecurringDecimal sol = new FractionToRecurringDecimal();
        System.out.println(sol.fractionToDecimal(1, 2)); // 0.5
        System.out.println(sol.fractionToDecimal(2, 1)); // 2
        System.out.println(sol.fractionToDecimal(2, 3)); // 0.(6)
        System.out.println(sol.fractionToDecimal(4, 333)); // 0.(012)
        System.out.println(sol.fractionToDecimal(1, 5)); // 0.2
        System.out.println(sol.fractionToDecimal(-1, 5)); // -0.2
        System.out.println(sol.fractionToDecimal(1, -5)); // -0.2
        System.out.println(sol.fractionToDecimal(1, 5)); // 0.2
        System.out.println(sol.fractionToDecimal(4, 9)); // 0.(4)
        System.out.println(sol.fractionToDecimal(-1, Integer.MIN_VALUE)); // 0.0000000004656612873077392578125
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Denominator cannot be null");
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();

        // sign
        if ((numerator > 0) != (denominator > 0)) sb.append('-');
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // integral part
        sb.append(num / den);

        // dot
        num = num % den;
        if (num == 0) {
            return sb.toString();
        } else sb.append(".");

        // fraction
        Map<Long, Integer> map = new HashMap<>();
        while (num != 0) {
            map.put(num, sb.length());

            num *= 10;
            sb.append(num / den);
            num %= den;

            Integer remainderIndex = map.get(num);
            if (remainderIndex != null) {
                sb.insert(remainderIndex, "(");
                sb.append(")");
                break;
            }
        }

        return sb.toString();
    }
}
