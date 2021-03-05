/**
 * Facebook Interview Preparation
 * <p>
 * Number of Visible Nodes
 * <p>
 * There is a binary tree with N nodes. You are viewing the tree from its left side and can see only the leftmost nodes at each level. Return the number of visible nodes.
 * <p>
 * Note: You can see only the leftmost nodes, but that doesn't mean they have to be left nodes. The leftmost node at a level could be a right node.
 * <p>
 * Signature
 * int visibleNodes(Node root) {
 * <p>
 * Input
 * The root node of a tree, where the number of nodes is between 1 and 1000, and the value of each node is between 0 and 1,000,000,000
 * <p>
 * Output
 * An int representing the number of visible nodes.
 * <p>
 * Example
 * <pre>
 *             8  <------ root
 *            / \
 *          3    10
 *         / \     \
 *        1   6     14
 *           / \    /
 *          4   7  13
 * </pre>
 * output = 4
 */
public class NumberOfVisibleNodes {
    static class Node {
        int data;
        Node left;
        Node right;

        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        NumberOfVisibleNodes sol = new NumberOfVisibleNodes();

        Node root_1 = new Node(8);
        root_1.left = new Node(3);
        root_1.right = new Node(10);
        root_1.left.left = new Node(1);
        root_1.left.right = new Node(6);
        root_1.right.right = new Node(14);
        root_1.left.right.left = new Node(4);
        root_1.left.right.right = new Node(7);
        root_1.right.right.left = new Node(13);
        int expected_1 = 4;
        int output_1 = sol.visibleNodes(root_1);
        System.out.println("visible nodes is " + output_1 + ", expected " + expected_1);

        Node root_2 = new Node(10);
        root_2.left = new Node(8);
        root_2.right = new Node(15);
        root_2.left.left = new Node(4);
        root_2.left.left.right = new Node(5);
        root_2.left.left.right.right = new Node(6);
        root_2.right.left = new Node(14);
        root_2.right.right = new Node(16);

        int expected_2 = 5;
        int output_2 = sol.visibleNodes(root_2);
        System.out.println("visible nodes is " + output_2 + ", expected " + expected_2);
    }

    private int depth = 0;

    public int visibleNodes(Node root) {
        if (root == null) return 0;
        depth = 0;
        visibleNodes0(root, 1);
        return depth;
    }

    private void visibleNodes0(Node node, int d) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            depth = Math.max(depth, d);
            return;
        }
        visibleNodes0(node.left, d + 1);
        visibleNodes0(node.right, d + 1);
    }
}
