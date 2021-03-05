/**
 * LeetCode
 * 670. Maximum Swap
 * https://leetcode.com/problems/maximum-swap/
 * #Medium
 */
public class MaximumSwap {
    public static void main(String[] args) {
        MaximumSwap sol = new MaximumSwap();
        System.out.println(sol.maximumSwap(2736)); // 7236
        System.out.println(sol.maximumSwap(9973)); // 9973
        System.out.println(sol.maximumSwap(3333)); // 3333
        System.out.println(sol.maximumSwap(9979)); // 9997
    }

    public int maximumSwap(int num) {
        String s = Integer.toString(num);
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] last = new int[10]; // last occurrences of digits
        for (int i = 0; i < n; i++) {
            last[chars[i] - '0'] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int d = 9; d > chars[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[last[d]];
                    chars[last[d]] = tmp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }
        return num;
    }
}
