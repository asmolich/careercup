import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode. 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{1})));  //true
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{1, 2})));  //true
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{1, 2, null, 3})));  //false
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{1, 2, 3})));  //true
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{1, 2, 3, 4, null, null, 5})));  //true
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{3, 9, 20, null, null, 15, 7})));  //true
        System.out.println(isBalanced(buildBstFromLevelOrder(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4})));  //false
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

    private static class State {
        int height = 0;
        boolean balanced = true;
    }

    private static boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        return isBalanced0(root).balanced;
    }

    private static State isBalanced0(TreeNode node) {
        if (node == null) return new State();

        State left = isBalanced0(node.left);
        if (!left.balanced) return left;
        State right = isBalanced0(node.right);
        if (!right.balanced) return right;

        State state = new State();
        state.balanced = Math.abs(left.height - right.height) <= 1;
        state.height = 1 + Math.max(left.height, right.height);
        return state;
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
