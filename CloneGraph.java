import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode
 * 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/
 * #Medium #Graph #BFS
 */
public class CloneGraph {
    public static void main(String[] args) {
        CloneGraph sol = new CloneGraph();
        Node three = new Node(3);
        Node node = new Node(1, Arrays.asList(new Node(2, Collections.singletonList(three)), new Node(4, Collections.singletonList(three))));
        // Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
        // Output: [[2,4],[1,3],[2,4],[1,3]]
        System.out.println("Original:\n" + node);
        System.out.println("Cloned:\n" + sol.cloneGraph(node));
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Deque<Node> deq = new ArrayDeque<>();
        deq.add(node);

        Map<Node, Node> bind = new HashMap<>();
        Node newHead = new Node(node.val);
        bind.put(node, newHead);

        while (!deq.isEmpty()) {
            Node current = deq.pollFirst();
            Node cloned = bind.get(current);
            for (Node neighbor : current.neighbors) {
                if (!bind.containsKey(neighbor)) {
                    Node neighborClone = new Node(neighbor.val);
                    bind.put(neighbor, neighborClone);
                    deq.add(neighbor);
                }
                cloned.neighbors.add(bind.get(neighbor));
            }
        }
        return newHead;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }

        @Override
        public String toString() {
            Deque<Node> deq = new ArrayDeque<>();
            deq.add(this);
            Set<Node> visited = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            while (!deq.isEmpty()) {
                int size = deq.size();
                for (int i = 0; i < size; i++) {
                    Node nd = deq.pollFirst();
                    if (nd == null) continue;
                    sb.append(nd.val).append('(').append(nd.hashCode()).append(')').append(',');
                    for (Node neighbor : nd.neighbors) {
                        if (visited.contains(neighbor)) continue;
                        deq.add(neighbor);
                        visited.add(neighbor);
                    }
                }
                sb.append("->");
            }
            return sb.toString();
        }
    }
}
