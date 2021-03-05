import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 * #Easy
 */
public class SymmetricTree {
    @SuppressWarnings("unused")
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

    public static void main(String[] args) {
        SymmetricTree sol = new SymmetricTree();
        System.out.println(sol.isSymmetric(buildBstFromLevelOrder(new Integer[]{1, 2, 2, 3, 4, 4, 3}))); // true
        System.out.println(sol.isSymmetric(buildBstFromLevelOrder(new Integer[]{1, 2, 2, null, 3, null, 3}))); // false
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric0(root, root);
    }

    public boolean isSymmetric0(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;

        return l.val == r.val &&
                isSymmetric0(l.left, r.right) &&
                isSymmetric0(l.right, r.left);
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