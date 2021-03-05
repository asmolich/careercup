import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 1123. Lowest Common Ancestor of Deepest Leaves
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 * #Medium #Tree
 */
public class LowestCommonAncestorOfDeepestLeaves {
    public static void main(String[] args) {
        LowestCommonAncestorOfDeepestLeaves sol = new LowestCommonAncestorOfDeepestLeaves();
        System.out.println(sol.lcaDeepestLeaves(buildBstFromLevelOrder(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}))); // [2,7,4]
        System.out.println(sol.lcaDeepestLeaves(buildBstFromLevelOrder(new Integer[]{1}))); // [1]
    }

    private TreeNode lca = null;
    private int maxDepth = 0;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        lca = null;
        maxDepth = 0;
        helper(root, 0);
        return lca;
    }

    private int helper(TreeNode node, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        if (node == null) return depth;
        int l = helper(node.left, depth + 1);
        int r = helper(node.right, depth + 1);
        if (l == r && l == maxDepth) lca = node;
        return Math.max(l, r);
    }

    private static class TreeNode {
        final int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            this(v, null, null);
        }

        TreeNode(int v, TreeNode l, TreeNode r) {
            val = v;
            left = l;
            right = r;
        }

        public String toString() {
            return "" + val;
        }
    }

    private static TreeNode buildBstFromLevelOrder(Integer[] order) {
        if (order == null || order.length == 0) return null;
        TreeNode head = null;
        Queue<TreeNode> parents = new ArrayDeque<>();
        TreeNode node;
        boolean left = true;
        for (Integer val : order) {
            node = val == null ? null : new TreeNode(val);
            if (head == null) {
                head = node;
                if (head == null) return null;
            }
            if (!parents.isEmpty()) {
                if (left) parents.peek().left = node;
                else parents.poll().right = node;
                left = !left;
            }
            if (node != null) parents.add(node);
        }

        return head;
    }
}
