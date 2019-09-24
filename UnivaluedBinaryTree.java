import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode. 965. Univalued Binary Tree
 * https://leetcode.com/problems/univalued-binary-tree/
 */
public class UnivaluedBinaryTree {
    public static void main(String[] args) {
        System.out.println(isUnivalTree(buildBstFromLevelOrder(new Integer[]{1})));
        System.out.println(isUnivalTree(buildBstFromLevelOrder(new Integer[]{1, 1})));
        System.out.println(isUnivalTree(buildBstFromLevelOrder(new Integer[]{1, 2})));
        System.out.println(isUnivalTree(buildBstFromLevelOrder(new Integer[]{1, 1, 1, 1, 1, null, 1})));
        TreeNode root = buildBstFromLevelOrder(new Integer[]{2, 2, 2, 5, 2});
//        System.out.println(root);
        System.out.println(isUnivalTree(root));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private static boolean isUnivalTree(TreeNode root) {
        if (root == null) return false;
        return isUnivalTree0(root);
    }

    private static boolean isUnivalTree0(TreeNode node) {
        boolean result = true;
        if (node.left != null) {
            result = (node.left.val == node.val) && isUnivalTree0(node.left);
        }
        if (result && node.right != null) {
            result = (node.right.val == node.val) && isUnivalTree0(node.right);
        }
        return result;
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
