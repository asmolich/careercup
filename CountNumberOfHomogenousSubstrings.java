/**
 * LeetCode
 * 1759. Count Number of Homogenous Substrings
 * https://leetcode.com/contest/weekly-contest-228/problems/count-number-of-homogenous-substrings/
 * #Medium
 */
public class CountNumberOfHomogenousSubstrings {
    public static void main(String[] args) {
        CountNumberOfHomogenousSubstrings sol = new CountNumberOfHomogenousSubstrings();
        System.out.println(sol.countHomogenous("abbcccaa")); // 13
        System.out.println(sol.countHomogenous("xy")); // 2
        System.out.println(sol.countHomogenous("zzzzz")); // 15
    }

    private static final int MOD = 1_000_000_007;

    public int countHomogenous(String s) {
        int count = 1, res = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                count = 1;
            }
            res = (res + count) % MOD;
        }
        return res;
    }

    @SuppressWarnings("unused")
    public int countHomogenousGeometricProgression(String s) {
        int n = s.length();
        // zzzzz
        // (1+5)*5/2 = 15
        // ab
        // 1+1
        // aa
        // 2*3/2=3
        long count = 0;
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                count += (i - start) * (i - start + 1L) / 2L;
                if (count >= MOD) count %= MOD;
                start = i;
            }
        }
        count += (n - start) * (n - start + 1L) / 2L;
        if (count >= MOD) count %= MOD;
        return (int) count;
    }
}
