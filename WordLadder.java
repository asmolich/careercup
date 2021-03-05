import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode
 * 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 * #Hard #BFS
 */
public class WordLadder {
    public static void main(String[] args) {
        WordLadder sol = new WordLadder();
        System.out.println(sol.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))); // 5
        System.out.println(sol.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"))); // 0
        System.out.println(sol.ladderLength("hot", "dog", Arrays.asList("hot", "dog"))); // 0
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty()) return 0;
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        int n = beginWord.length();
        if (endWord.length() != n) return 0;
        Deque<String> deq = new ArrayDeque<>();
        deq.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int count = 0;
        while (!deq.isEmpty()) {
            int size = deq.size();
            // System.out.println(deq);
            for (int i = 0; i < size; i++) {
                String word = deq.removeFirst(); // queue -> BFS
                if (word.equals(endWord)) return count + 1;
                char[] chars = word.toCharArray();
                for (int j = 0; j < n; j++) {
                    char orig = chars[j];
                    for (int k = 0; k < 26; k++) {
                        chars[j] = (char) ('a' + k);
                        if (chars[j] == orig) continue;
                        String str = new String(chars);
                        if (dict.contains(str) && !visited.contains(str)) {
                            deq.add(str);
                            visited.add(str);
                        }
                    }
                    chars[j] = orig;
                }
            }
            count++;
        }
        return 0;
    }
}
