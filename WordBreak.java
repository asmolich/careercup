import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode
 * 139. Word Break
 * https://leetcode.com/problems/word-break/
 * Solution: https://www.geeksforgeeks.org/word-break-problem-dp-32/
 * #Medium #DP
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak sol = new WordBreak();
        System.out.println(sol.wordBreakDP("leetcode", Arrays.asList("leet", "code"))); // true
        System.out.println(sol.wordBreakDP("applepenapple", Arrays.asList("apple", "pen"))); // true
        System.out.println(sol.wordBreakDP("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))); // false
    }

    public boolean wordBreakDP(String s, List<String> wordDict) {
        int n = s.length();
        if (n == 0) return true;

        return wordBreakDP0(s, new HashSet<>(wordDict));
    }

    private boolean wordBreakDP0(String s, Set<String> dict) {
        int n = s.length();
        if (n == 0) return true;

        boolean[] dp = new boolean[n + 1];

        // matchedIndex array represents the indexes for which
        // dp[i] is true. Initially only -1 element is present
        // in this array.
        List<Integer> matchedIndex = new ArrayList<>(n + 1);
        matchedIndex.add(-1);

        for (int i = 0; i < n; i++) {
            int mSize = matchedIndex.size();

            // Flag value which tells that a substring matches
            // with given words or not.
            boolean flag = false;

            // Check all the substring from the indexes matched earlier.
            // If any of that substring matches than raise the flag.
            for (int j = mSize - 1; j >= 0; j--) {

                // sb is substring starting from matched_index[j]
                // + 1  and of length i - matched_index[j]
                String sb = s.substring(matchedIndex.get(j) + 1, i + 1);

                if (dict.contains(sb)) {
                    flag = true;
                    break;
                }
            }

            // If substring matches than do dp[i] = true and
            // push that index in matched_index array.
            if (flag) {
                dp[i] = true;
                matchedIndex.add(i);
            }
        }
        return dp[n - 1];
    }

    @SuppressWarnings("unused")
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dictLetters = new int[26];
        int[] wordLetters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            wordLetters[s.charAt(i) - 'a']++;
        }
        Map<Integer, Set<String>> words = new HashMap<>();
        for (String word : wordDict) {
            int len = word.length();
            for (int i = 0; i < len; i++) {
                dictLetters[word.charAt(i) - 'a']++;
            }
            Set<String> strings = words.computeIfAbsent(len, k -> new HashSet<>());
            strings.add(word);
        }

        for (int i = 0; i < wordLetters.length; i++) {
            if (wordLetters[i] > 0 && dictLetters[i] == 0) return false;
        }

        return wordBreak0(s, words);
    }

    private boolean wordBreak0(String s, Map<Integer, Set<String>> words) {
        // System.out.println(words);
        // System.out.println(s);
        for (Map.Entry<Integer, Set<String>> entry : words.entrySet()) {
            Integer key = entry.getKey();
            Set<String> value = entry.getValue();

            if (key > s.length()) continue;

            String substr = s.substring(0, key);
            if (value.contains(substr) && (key == s.length() || wordBreak0(s.substring(key), words))) {
                return true;
            }
        }

        return false;
    }
}
