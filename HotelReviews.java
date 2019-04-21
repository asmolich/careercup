import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HotelReviews {
    public static void main(String[] args) {
        System.out.println(new HotelReviews().solve("cool_ice_wifi", new ArrayList<>(Arrays.asList("water_is_cool", "cold_ice_drink", "cool_wifi_speed"))));
        System.out.println(new HotelReviews().solve("play_boy", new ArrayList<>(Arrays.asList("smart_guy", "girl_and_boy_play", "play_ground"))));
    }

    public ArrayList<Integer> solve(String s, ArrayList<String> r) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (s == null || s.isEmpty() || r == null || r.isEmpty()) return result;

        TrieNode head = buildTrie(s);
        System.out.println("============");
        printTrie(head);
        if (head == null) return result;

        System.out.println("============");
        LinkedHashMap<Integer, Integer> order = new LinkedHashMap<>();
        TrieNode node = head;
        int index = 0;
        for (String review : r) {
            order.put(index, 0);
            //System.out.println(review);
            // process a review
            for (int i = 0; i < review.length(); i++) {
                //System.out.println(review.charAt(i));
                if (review.charAt(i) == '_') {
                    //System.out.println(node);
                    if (node != null && node.isTerminal)
                        order.put(index, order.get(index) + 1);
                    node = head;
                    continue;
                }
                if (node != null) node = node.children[review.charAt(i) - 'a'];
            }
            //System.out.println(node);
            if (node != null && node.isTerminal)
                order.put(index, order.get(index) + 1);
            index++;

            node = head;
        }
        System.out.println("order = " + order);
        System.out.println("============");

        return new ArrayList<>(
            order.entrySet()
                .stream()
                .sorted((a, b) -> -Integer.compare(a.getValue(), b.getValue()))
                .map(e -> e.getKey())
                .collect(Collectors.toList())
        );
    }

    public TrieNode buildTrie(String s) {
        TrieNode head = new TrieNode();
        TrieNode parent = head;
        TrieNode node = null;
        System.out.println("s=" + s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                parent.isTerminal = true;
                parent = head;
                continue;
            }
            node = new TrieNode();
            parent.children[s.charAt(i) - 'a'] = node;
            parent = node;
        }
        node.isTerminal = true;
        return head;
    }

    void printTrie(TrieNode head) {
        Deque<TrieNode> q = new ArrayDeque<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            TrieNode node = q.pollLast();
            System.out.println(node);
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    q.addLast(node.children[i]);
                }
            }
        }
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isTerminal = false;

        @Override
        public String toString() {
            return "TrieNode{" +
                "children=" + IntStream.range(0, children.length)
                .mapToObj(i -> children[i] == null ? (char) 0 : (char) (i + 'a'))
                .filter(c -> c > 0)
                .collect(Collectors.toList()) +
                ", isTerminal=" + isTerminal +
                '}';
        }
    }
}

