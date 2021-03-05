import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 211. Design Add and Search Words Data Structure
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * #Medium #Trie
 */
public class DesignAddAndSearchWordsDataStructure {
    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad")); // false
        System.out.println(dict.search("bad")); // true
        System.out.println(dict.search(".ad")); // true
        System.out.println(dict.search("b..")); // true
    }

    private static class WordDictionary {
        private static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isTerminal = false;

            public String toString() {
                return "(" + toString(children) + (isTerminal ? ",T" : "") + ")";
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

        private static class SearchNode {
            TrieNode node;
            int i;

            public String toString() {
                return "{" + i + "}";
            }
        }

        TrieNode head;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            head = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            if (word == null || word.isEmpty()) return;

            TrieNode node = head;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode newNode = node.children[ch - 'a'];
                if (newNode == null) {
                    newNode = new TrieNode();
                    node.children[ch - 'a'] = newNode;
                }
                node = newNode;
            }
            node.isTerminal = true;

            // System.out.println(head);
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            // System.out.print("search: " + word + ", ");
            if (word == null || word.isEmpty()) return false;

            SearchNode searchNode = new SearchNode();
            searchNode.node = head;

            Deque<SearchNode> deq = new ArrayDeque<>();
            deq.add(searchNode);
            while (!deq.isEmpty()) {
                // System.out.println(deq);
                searchNode = deq.poll();
                // System.out.println("word.length() = " + word.length());
                // System.out.println("searchNode.i = " + searchNode.i);
                // System.out.println("searchNode.node.isTerminal = " + searchNode.node.isTerminal);
                if (searchNode.i == word.length()) {
                    if (searchNode.node.isTerminal) {
                        // System.out.println("result: true");
                        return true;
                    } else {
                        continue;
                    }
                }
                char ch = word.charAt(searchNode.i);
                // System.out.println("ch = " + ch);
                if (ch == '.') {
                    // System.out.print("search in children of: ");
                    for (int i = 0; i < searchNode.node.children.length; i++) {
                        if (searchNode.node.children[i] != null) {
                            // System.out.print("" + (char)(i + 'a') + ", ");
                            SearchNode sn = new SearchNode();
                            sn.node = searchNode.node.children[i];
                            sn.i = searchNode.i + 1;
                            deq.add(sn);
                        }
                    }
                } else {
                    if (searchNode.node.children[ch - 'a'] != null) {
                        searchNode.node = searchNode.node.children[ch - 'a'];
                        searchNode.i = searchNode.i + 1;
                        deq.add(searchNode);
                    }
                }
            }
            // System.out.println("result: false");
            return false;
        }
    }
}
