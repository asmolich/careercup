import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode
 * 140. Word Break II
 * https://leetcode.com/problems/word-break-ii/
 * #Hard
 */
public class WordBreakII {
    public static void main(String[] args) {
        WordBreakII sol = new WordBreakII();
        System.out.println(sol.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"))); // ["cats and dog","cat sand dog"]
//        System.out.println(sol.wordBreak("pineapplepenapple",
//                Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"))); // ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) return Collections.emptyList();

        //noinspection unchecked
        List<String>[] dp = new List[s.length() + 1];
        dp[0] = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (dp[i] == null) continue;

            for (String word : wordDict) {
                int len = word.length();
                int end = i + len;
                if (end > s.length()) continue;

                if (s.substring(i, end).equals(word)) {
                    if (dp[end] == null) {
                        dp[end] = new ArrayList<>();
                    }
                    dp[end].add(word);
                }
            }
        }

        System.out.println(Arrays.toString(dp));

        List<String> res = new ArrayList<>();
        wordBreak0(s, new HashSet<>(wordDict), res);
        return res;
    }

    private static class StrInt {
        String str;
        int i;

        @Override
        public String toString() {
            return str + " at i=" + i;
        }
    }

    private void wordBreak0(String s, Set<String> dict, List<String> res) {
        int n = s.length();
        List<StrInt> dp = new ArrayList<>(n + 1);

        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                String sub = s.substring(j, i + 1);
                if (dict.contains(sub)) {
                    StrInt word = new StrInt();
                    word.str = sub;
                    word.i = j;
                    dp.add(word);
                }
            }
        }
        System.out.println(dp);
    }
}
