import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode. 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        System.out.println(minDepth(buildBstFromLevelOrder(new Integer[]{1})));  //1
        System.out.println(minDepth(buildBstFromLevelOrder(new Integer[]{1, 2})));  //2
        System.out.println(minDepth(buildBstFromLevelOrder(new Integer[]{1, 2, 3})));  //2
        System.out.println(minDepth(buildBstFromLevelOrder(new Integer[]{1, 2, 3, 4, null, null, 5})));  //3
        System.out.println(minDepth(buildBstFromLevelOrder(new Integer[]{3, 9, 20, null, null, 15, 7})));  //2
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

    private static int minDepth(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        int minLeft = minDepth(node.left);
        int minRight = minDepth(node.right);
        if (minLeft > 0 && minRight > 0) return 1 + Math.min(minLeft, minRight);
        return 1 + Math.max(minLeft, minRight);
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
