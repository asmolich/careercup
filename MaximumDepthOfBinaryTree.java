import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        System.out.println(maxDepth(buildBstFromLevelOrder(new Integer[]{1})));  //1
        System.out.println(maxDepth(buildBstFromLevelOrder(new Integer[]{1, 2})));  //2
        System.out.println(maxDepth(buildBstFromLevelOrder(new Integer[]{1, 2, 3})));  //2
        System.out.println(maxDepth(buildBstFromLevelOrder(new Integer[]{1, 2, 3, 4, null, null, 5})));  //3
        System.out.println(maxDepth(buildBstFromLevelOrder(new Integer[]{3, 9, 20, null, null, 15, 7})));  //3
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

    private static int maxDepth(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
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
