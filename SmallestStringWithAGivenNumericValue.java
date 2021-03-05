/**
 * LeetCode
 * 1663. Smallest String With A Given Numeric Value
 * https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
 * #Medium #Greedy
 */
public class SmallestStringWithAGivenNumericValue {
    public static void main(String[] args) {
        SmallestStringWithAGivenNumericValue sol = new SmallestStringWithAGivenNumericValue();
        System.out.println(sol.getSmallestString(3, 27)); // aay
        System.out.println(sol.getSmallestString(5, 73)); // aaszz
    }

    public String getSmallestString(int n, int k) {
        if (k < n) return "";

        char[] res = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i + 26 <= k) {
                res[i] = 'z';
                k -= 26;
            } else {
                int x = k - i;
                res[i] = (char) (x - 1 + 'a');
                k -= x;
            }
        }
        return new String(res);
    }
}
