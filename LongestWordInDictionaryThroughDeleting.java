import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode
 * 524. Longest Word in Dictionary through Deleting
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * #Medium
 */
public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        LongestWordInDictionaryThroughDeleting sol = new LongestWordInDictionaryThroughDeleting();
        System.out.println(sol.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea"))); // "apple"
        System.out.println(sol.findLongestWord("abpcplea", Arrays.asList("a", "b", "c"))); // "a"
        System.out.println(sol.findLongestWord("bab", Arrays.asList("ba", "ab", "a", "b"))); // "ab"
    }

    /*
    Time complexity: O(n⋅xlogn+n⋅x). Here n refers to the number of strings in list dd and x refers to average string length.
    Sorting takes O(nlogn) and isSubsequence takes O(x) to check whether a string is a subsequence of another string or not.
     */
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.isEmpty() || d == null || d.isEmpty()) return "";

        d.sort(Comparator.comparingInt(String::length).reversed().thenComparing(a -> a));

        for (String word : d) {
            if (isSubseq(s, word)) return word;
        }
        return "";
    }

    private boolean isSubseq(String s, String word) {
        int n = s.length();
        if (n < word.length()) return false;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == word.charAt(j)) {
                j++;
                if (j >= word.length()) return true;
            }
        }
        return false;
    }


    @SuppressWarnings("unused")
    // bug appears in larger dataset
    public String findLongestWordTrie(String s, List<String> d) {
        if (s == null || s.isEmpty() || d == null || d.isEmpty()) return "";

        int n = s.length();
        TrieNode root = buildTrie(d);
        System.out.println(root);
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (root.children[ch - 'a'] != null) {
                deq.add(i);
            }
        }

        String res = "";
        int maxLen = 0;
        while (!deq.isEmpty()) {
            TrieNode node = root;
            int idx = deq.removeFirst();
            while (idx < n) {
                char ch = s.charAt(idx);
                if (node.children[ch - 'a'] != null) {
                    node = node.children[ch - 'a'];
                }
                idx++;
                if (node.isTerminal) {
                    if (maxLen < node.word.length()) {
                        maxLen = node.word.length();
                        res = node.word;
                    } else if (maxLen == node.word.length()) {
                        if (res.compareTo(node.word) > 0) res = node.word;
                    }
                }
            }
        }

        return res;
    }

    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            add(root, word);
        }
        return root;
    }

    private void add(TrieNode root, String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) node.children[ch - 'a'] = new TrieNode();
            node = node.children[ch - 'a'];
        }
        node.isTerminal = true;
        node.word = word;
    }

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isTerminal = false;
        private String word;

        @Override
        public String toString() {
            return "(" + toString(children) + (isTerminal ? ",T" : "") + (word == null ? "" : "," + word + "") + ")";
        }

        private String toString(TrieNode[] children) {
            if (children == null) return "";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null) {
                    sb.append((char) (i + 'a'))
                            .append(children[i].toString())
                            .append(',');
                }
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 1);
            }
            return sb.toString();
        }
    }
}
