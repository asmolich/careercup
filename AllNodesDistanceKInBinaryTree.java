import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode
 * 863. All Nodes Distance K in Binary Tree
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * #Medium #Tree
 */
public class AllNodesDistanceKInBinaryTree {
    public static void main(String[] args) {
        AllNodesDistanceKInBinaryTree sol = new AllNodesDistanceKInBinaryTree();
        TreeNode[] nodes = buildBstFromLevelOrder(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5);
        System.out.println(sol.distanceK(nodes[0], nodes[1], 2)); // [7, 4, 1]
        nodes = buildBstFromLevelOrder(new Integer[]{0, 2, 1, null, null, 3}, 3);
        System.out.println(sol.distanceK(nodes[0], nodes[1], 3)); // [2]
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null || target == null) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        fillParents(root, null, parents);
        search(target, null, k, parents, res);
        return res;
    }

    private void search(TreeNode node, TreeNode last, int k, Map<TreeNode, TreeNode> parents, List<Integer> res) {
        if (node == null) return;
        if (k == 0) {
            res.add(node.val);
            return;
        }

        if (node.left != last) {
            search(node.left, node, k - 1, parents, res);
        }
        if (node.right != last) {
            search(node.right, node, k - 1, parents, res);
        }
        if (parents.get(node) != last) {
            search(parents.get(node), node, k - 1, parents, res);
        }
    }

    private void fillParents(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parents) {
        if (node == null) return;
        parents.put(node, parent);
        fillParents(node.left, node, parents);
        fillParents(node.right, node, parents);
    }

    private static TreeNode[] buildBstFromLevelOrder(Integer[] order, int target) {
        if (order == null || order.length == 0) return null;
        TreeNode head = null;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        TreeNode node;
        TreeNode targetNode = null;
        boolean left = true;
        for (Integer val : order) {
            node = val == null ? null : new TreeNode(val);
            if (head == null) {
                head = node;
                if (head == null) return null;
            }
            if (!nodes.isEmpty()) {
                if (left) nodes.peek().left = node;
                else nodes.poll().right = node;
                left = !left;
            }
            if (node != null) {
                nodes.add(node);
                if (targetNode == null && node.val == target) targetNode = node;
            }
        }

        return new TreeNode[]{head, targetNode};
    }
}
