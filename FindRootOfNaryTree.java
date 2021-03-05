import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode
 * 1506. Find Root of N-ary Tree
 * https://leetcode.com/discuss/interview-question/351946/google-onsite-find-root-of-n-ary-tree
 *
 * <p>
 * Given an N-ary tree as a list of nodes Node[] tree. Each node has a unique value:
 * <pre>
 * class Node {
 *     int val;
 *     List<Node> children = new ArrayList<>();
 * 	   Node(int val) { this.val = val; }
 * }
 * </pre>
 * Find and return its root.
 * <p>
 * public Node findRoot(Node[] tree) {}
 * Example 1:
 * <pre>
 * 		     1
 *       /   |   \
 *      2    3    4
 * </pre>
 * Node n1 = new Node(1);
 * Node n2 = new Node(2);
 * Node n3 = new Node(3);
 * Node n4 = new Node(4);
 * <p>
 * n1.children.add(n2);
 * n1.children.add(n3);
 * n1.children.add(n4);
 * <p>
 * Input: [n2, n3, n1, n4]
 * Output: n1
 * <p>
 * https://blog.csdn.net/qq_21201267/article/details/107380727
 */
public class FindRootOfNaryTree {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static class Node {
        final int val;
        final List<Node> children = new ArrayList<>();

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        FindRootOfNaryTree sol = new FindRootOfNaryTree();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.children.add(n2);
        n1.children.add(n3);
        n1.children.add(n4);

        System.out.println("Expected 1, actual: " + sol.findRoot(new Node[]{n2, n3, n4, n1}).val);

        n1 = new Node(-1);
        n2 = new Node(2);
        n3 = new Node(3);
        n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);

        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);
        n5.children.add(n6);
        n6.children.add(n7);
        n7.children.add(n8);
        n8.children.add(n9);
        n9.children.add(n10);

        System.out.println("Expected -1, actual: " + sol.findRoot(new Node[]{n2, n3, n4, n1, n10, n6, n8, n7, n9, n5}).val);
    }

    public Node findRoot(Node[] tree) {
        // A ^ 0 == A
        // A ^ A == 0

        int xor = 0;
        for (Node node : tree) {
            xor ^= node.val;
            for (Node child : node.children) {
                xor ^= child.val;
            }
        }
        for (Node node : tree) {
            if (node.val == xor) return node;
        }
        return null;
    }
}
