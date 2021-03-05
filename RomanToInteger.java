import java.util.Map;

/**
 * LeetCode
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 * #Easy
 */
public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger sol = new RomanToInteger();
        System.out.println(sol.romanToInt("III")); // 3
        System.out.println(sol.romanToInt("IV")); // 4
        System.out.println(sol.romanToInt("IX")); // 9
        System.out.println(sol.romanToInt("LVIII")); // 58
        System.out.println(sol.romanToInt("MCMXCIV")); // 1994
    }

    private static final Map<Character, Integer> table = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        int res = 0;
        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            int value = table.getOrDefault(ch, 0);
            if (value <= prev) res += value;
            else res = res - 2 * prev + value;
            prev = value;
        }
        return res;
    }
}
