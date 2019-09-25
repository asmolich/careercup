import java.util.List;

/**
 * LeetCode. 559. Maximum Depth of N-ary Tree
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaximumDepthOfNaryTree {
    public static void main(String[] args) {
        // tests
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public int maxDepth(Node node) {
        if (node == null) return 0;

        int max = 0;
        for (Node child : node.children) {
            max = Math.max(max, maxDepth(child));
        }
        return 1 + max;
    }
}
