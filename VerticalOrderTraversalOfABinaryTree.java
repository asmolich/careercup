import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * LeetCode
 * 987. Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * #Hard #Tree
 */
public class VerticalOrderTraversalOfABinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Node implements Comparable<Node> {
        int y;
        int val;

        public int compareTo(Node node) {
            int cmp = Integer.compare(y, node.y);
            if (cmp == 0) {
                cmp = Integer.compare(val, node.val);
            }
            return cmp;
        }
    }

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Map<Integer, SortedSet<Node>> layers = new HashMap<>();
        verticalTraversal(root, 0, 0, layers);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(layers.get(i).stream().map(t -> t.val).collect(Collectors.toList()));
        }
        return result;
    }

    private void verticalTraversal(TreeNode node, int x, int y, Map<Integer, SortedSet<Node>> layers) {
        if (node == null) return;

        min = Math.min(min, x);
        max = Math.max(max, x);

        SortedSet<Node> layer = layers.computeIfAbsent(x, k -> new TreeSet<>());

        Node n = new Node();
        n.val = node.val;
        n.y = y;
        layer.add(n);

        verticalTraversal(node.left, x - 1, y + 1, layers);
        verticalTraversal(node.right, x + 1, y + 1, layers);
    }
}
