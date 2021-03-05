/**
 * LeetCode
 * 1358. Number of Substrings Containing All Three Characters
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 * #Medium #SlidingWindow
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        NumberOfSubstringsContainingAllThreeCharacters sol = new NumberOfSubstringsContainingAllThreeCharacters();
        System.out.println(sol.numberOfSubstrings("")); // 0
        System.out.println(sol.numberOfSubstrings("abcabc")); // 10
        System.out.println(sol.numberOfSubstrings("aaacb")); // 3
        System.out.println(sol.numberOfSubstrings("acb")); // 1
        System.out.println(sol.numberOfSubstrings("acbbcac")); // 11
    }

    public int numberOfSubstrings(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int count = 0;
        int lo = 0;
        int a = 0, b = 0, c = 0;
        for (int hi = 0; hi < n; hi++) {
            char ch = s.charAt(hi);
            if (ch == 'a') a++;
            else if (ch == 'b') b++;
            else if (ch == 'c') c++;

            while (lo < hi && a >= 1 && b >= 1 && c >= 1) {
                char ch2 = s.charAt(lo++);
                if (ch2 == 'a') a--;
                else if (ch2 == 'b') b--;
                else if (ch2 == 'c') c--;
                count += n - hi;
            }
        }
        return count;
    }
}
