import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Facebook Interview Preparation
 * <p>
 * Nodes in a Subtree
 * <p>
 * You are given a tree that contains N nodes, each containing an integer u which corresponds to a lowercase character c in the string s using 1-based indexing.
 * <p>
 * You are required to answer Q queries of type [u, c], where u is an integer and c is a lowercase letter. The query result is the number of nodes in the subtree of node u containing c.
 * <p>
 * Signature
 * int[] countOfNodes(Node root, ArrayList<Query> queries, String s)
 * <p>
 * Input
 * A pointer to the root node, an array list containing Q queries of type [u, c], and a string s
 * <p>
 * Constraints
 * N and Q are the integers between 1 and 1,000,000
 * u is a unique integer between 1 and N
 * s is of the length of N, containing only lowercase letters
 * c is a lowercase letter contained in string s
 * Node 1 is the root of the tree
 * <p>
 * Output
 * An integer array containing the response to each query
 * <p>
 * Example
 * <pre>
 *         1(a)
 *         /   \
 *       2(b)  3(a)
 * </pre>
 * s = "aba"
 * RootNode = 1
 * query = [[1, 'a']]
 * <p>
 * Note: Node 1 corresponds to first letter 'a', Node 2 corresponds to second letter of the string 'b', Node 3 corresponds to third letter of the string 'a'.
 * <p>
 * output = [2]
 * <p>
 * Both Node 1 and Node 3 contain 'a', so the number of nodes within the subtree of Node 1 containing 'a' is 2.
 * <p>
 * <p>
 * It takes O(n*m) if we loop through n elements for each query. A O(n+m) solution is possible if we use data structure to help.
 * <p>
 * Basic idea:
 * - use a Map to store the count of each character in the subtree
 * - a node's Map is the consolidated result of all its children + itself
 * - once the Map is built, go through queries and do lookup in the map
 */
public class NodesInASubtree {
    // Tree Node
    @SuppressWarnings("unused")
    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            val = 0;
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private static class Query {
        int u;
        char c;

        Query(int u, char c) {
            this.u = u;
            this.c = c;
        }

        @Override
        public String toString() {
            return "[" + u + ", " + c + ']';
        }
    }

    public static void main(String[] args) {
        NodesInASubtree sol = new NodesInASubtree();

        //Testcase 1
        String s_1 = "aba";
        Node root_1 = new Node(1);
        root_1.children.add(new Node(2));
        root_1.children.add(new Node(3));
        ArrayList<Query> queries_1 = new ArrayList<>();
        queries_1.add(new Query(1, 'a'));
        int[] output_1 = sol.countOfNodes(root_1, queries_1, s_1);
        int[] expected_1 = {2};
        System.out.println("1 count = " + Arrays.toString(output_1) + ", expected = " + Arrays.toString(expected_1));
        System.out.println("2 count = " + Arrays.toString(sol.countOfNodes(root_1, queries_1, s_1)) + ", expected = " + Arrays.toString(expected_1));

        // Testcase 2
        String s_2 = "abaacab";
        Node root_2 = new Node(1);
        root_2.children.add(new Node(2));
        root_2.children.add(new Node(3));
        root_2.children.add(new Node(7));
        root_2.children.get(0).children.add(new Node(4));
        root_2.children.get(0).children.add(new Node(5));
        root_2.children.get(1).children.add(new Node(6));
        ArrayList<Query> queries_2 = new ArrayList<>();
        queries_2.add(new Query(1, 'a'));
        queries_2.add(new Query(2, 'b'));
        queries_2.add(new Query(3, 'a'));
        int[] output_2 = sol.countOfNodes(root_2, queries_2, s_2);
        int[] expected_2 = {4, 1, 2};
        System.out.println("1 count = " + Arrays.toString(output_2) + ", expected = " + Arrays.toString(expected_2));
        System.out.println("2 count = " + Arrays.toString(sol.countOfNodes2(root_2, queries_2, s_2)) + ", expected = " + Arrays.toString(expected_2));
    }

    public int[] countOfNodes(Node root, ArrayList<Query> queries, String s) {
        if (queries == null || queries.isEmpty() || s == null || s.isEmpty() || root == null) return new int[0];

        int m = queries.size();
        int[] res = new int[m];
        int index = 0;
        for (Query q : queries) {
            res[index++] = countOfNodes0(root, q, s, false);
        }
        return res;
    }

    private int countOfNodes0(Node node, Query q, String s, boolean calculate) {
        if (node == null) return 0;
        // System.out.println(Map.of("query", q, "node", node.val, "calc", calculate, "ch", s.charAt(node.val - 1)));
        int count = 0;
        if (calculate) {
            count += s.charAt(node.val - 1) == q.c ? 1 : 0;
            for (Node ch : node.children) {
                count += countOfNodes0(ch, q, s, true);
            }
        } else {
            if (node.val == q.u) {
                // found
                count += countOfNodes0(node, q, s, true);
            } else {
                for (Node ch : node.children) {
                    count += countOfNodes0(ch, q, s, false);
                }
            }
        }
        return count;
    }

    //=====================================

    private int[] dfs(Node node, String s, int[][] countsPerIndex) {
        int[] charCounts = new int[26];
        char ch = s.charAt(node.val - 1);
        charCounts[ch - 'a']++;

        for (Node child : node.children) {
            int[] childCounts = dfs(child, s, countsPerIndex);
            for (int i = 0; i < childCounts.length; i++) {
                charCounts[i] += childCounts[i];
            }
        }

        countsPerIndex[node.val - 1] = charCounts;
        return charCounts;
    }

    public int[] countOfNodes2(Node root, ArrayList<Query> queries, String s) {
        if (queries == null || queries.isEmpty() || s == null || s.isEmpty() || root == null) return new int[0];

        int n = s.length();
        int m = queries.size();
        int[] res = new int[m];

        int[][] countsPerIndex = new int[n][26];
        dfs(root, s, countsPerIndex);

        int index = 0;
        for (Query q : queries) {
            res[index++] = countsPerIndex[q.u - 1][q.c - 'a'];
        }

        return res;
    }
}
