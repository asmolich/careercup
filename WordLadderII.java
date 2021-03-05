import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * LeetCode
 * 126. Word Ladder
 * https://leetcode.com/problems/word-ladder-ii/
 * #Hard #BFS
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII sol = new WordLadderII();
        System.out.println(sol.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))); // [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
        System.out.println(sol.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"))); // []
        System.out.println(sol.findLadders("hot", "dog", Arrays.asList("hot", "dog"))); // []
    }

    private static class Node implements Comparable<Node> {
        private final String word;
        private final List<String> path;

        public Node(String word, List<String> path) {
            this.word = word;
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(word, node.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word);
        }

        @Override
        public int compareTo(Node o) {
            return word.compareTo(o.word);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "word='" + word + '\'' +
                    ", path=" + path +
                    '}';
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.isEmpty()) return Collections.emptyList();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return Collections.emptyList();
        int n = beginWord.length();
        if (endWord.length() != n) return Collections.emptyList();
        Deque<Node> deq = new ArrayDeque<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        deq.add(new Node(beginWord, path));
        Set<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        boolean found = false;
        while (!deq.isEmpty()) {
            int size = deq.size();
//            System.out.println(deq);
            for (int i = 0; i < size; i++) {
                Node node = deq.removeFirst();
                String word = node.word;
                visited.add(word);
                if (word.equals(endWord)) {
                    res.add(node.path);
                    found = true;
                }
                char[] chars = word.toCharArray();
                for (int j = 0; j < n; j++) {
                    char orig = chars[j];
                    for (int k = 0; k < 26; k++) {
                        chars[j] = (char) ('a' + k);
                        if (chars[j] == orig) continue;
                        String str = new String(chars);
                        if (dict.contains(str) && !visited.contains(str)) {
                            List<String> newPath = new ArrayList<>(node.path);
                            newPath.add(str);
                            deq.add(new Node(str, newPath));
                        }
                    }
                    chars[j] = orig;
                }
            }
            if (found) break;
        }
        return res;
    }
}
