/**
 * LeetCode
 * 767. Reorganize String
 * https://leetcode.com/problems/reorganize-string/
 * #Medium
 */
public class ReorganizeString {
    public static void main(String[] args) {
        ReorganizeString sol = new ReorganizeString();
        System.out.println(sol.reorganizeString("aab")); // "aba"
        System.out.println(sol.reorganizeString("aaab")); // ""
        System.out.println(sol.reorganizeString("vvvlo")); // "vlvov"
        System.out.println(sol.reorganizeString("kkkkzrkatkwpkkkktrq")); //
        // "krkrktktkakpkqkwkzk"
        // "kakpkqkrkrktktkwkzk"
        System.out.println(sol.reorganizeString("todrnphcamnomskfrhe")); //
        // "hahcmdmenfnkoposrtr"
        // "hnhnaocodperfrksmtm"
    }

    public String reorganizeString(String s) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        int[] fr = new int[26];
        int max = 0;
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            fr[ch - 'a']++;
            if (fr[ch - 'a'] >= max) {
                maxChar = ch;
                max = fr[ch - 'a'];
            }
        }
        if ((n & 1) == 0 && max > n / 2 || max > n / 2 + 1) return "";

        char[] res = new char[n];
        int idx = 0;
        while (max-- > 0) {
            res[idx] = maxChar;
            idx += 2;
        }
        fr[maxChar - 'a'] = 0;
        for (int i = 0; i < 26; i++) {
            while (fr[i] > 0) {
                if (idx >= n) idx = 1;
                res[idx] = (char) (i + 'a');
                fr[i]--;
                idx += 2;
            }
        }
        return new String(res);
    }
}
