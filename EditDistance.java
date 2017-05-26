import java.util.*;
public class EditDistance {
    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        String[][] samples = new String[][]{
            {"abc", "abc"},
            {"bce", "abcde"},
            {"Anshuman Antihuman", ""},
            {"Anshuman", "Antihuman"},
            {null, null},
            {"", null},
            {null, ""},
            {"", ""},
            {"aaa", "aa"},
            {"bbbaabaa", "aababbabb"},
            {"abaaaaaa", "aaaaaaa"},
            {"bceb", "abcdeab"},
            {"baaaab", "bbbbabbbabbbbb"},
            {"bbbba", "b"}
        };
        for (String[] data : samples) {
            System.out.println("Distance between \"" + data[0]+ "\" and \"" + data[1] + "\" is " + ed.distance(data[0], data[1]));
        }
    }

    public int distance(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null || s1.isEmpty() && s2 != null) return s2.length();
        if (s2 == null || s2.isEmpty() && s1 != null) return s1.length();
        if (s1.equals(s2)) return 0;

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; dp[i][0] = i++);
        for (int j = 1; j <= m; dp[0][j] = j++);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char s1i = s1.charAt(i - 1);
                char s2j = s2.charAt(j - 1);

                dp[i][j] = s1i == s2j ? dp[i - 1][j - 1] : (Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1);
            }
        }

        StringBuilder sb = new StringBuilder("     ");
        for (int i = 0; i <= m; i++) {
            sb.append(i == 0 ? '_' : s2.charAt(i - 1)).append("  ");
        }
        System.out.println(sb);
        for (int i = 0; i <= n; i++) {
            System.out.println((i == 0 ? '_' : s1.charAt(i - 1)) + " | " + Arrays.toString(dp[i]));
        }

        return dp[n][m]; 
    }
    private int costOfReplace(char a, char b) {
        return a == b ? 0 : 1;
    }
    private int costOfInsert(char a, char b) {
        return 1;
    }
    private int costOfDelete(char a, char b) {
        return 1;
    }
}

// s1 = bceb
// s2 = abcdeab
//   | a | b | c | d | e | a | b
// b | 1 | 1 | 2 | 3 | 4 | 5 | 5?
// c | 2 | 2 | 1 | 2 | 3 | 4 | 5
// e | 3 | 3 | 2 | 2 | 2 | 3 | 4
// b | 4 | 3 | 3 | 3 | 3 | 3 | 3
//

