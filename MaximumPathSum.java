import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode. 124. Binary Tree Maximum Path Sum. https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class MaximumPathSum {
    public static void main(String[] args) {
        System.out.println(maxPathSum(buildBstFromLevelOrder(new Integer[]{4, 2, 6, 1, 3, null, 7})));
        System.out.println(maxPathSum(buildBstFromLevelOrder(new Integer[]{1, 2, 3})));
        System.out.println(maxPathSum(buildBstFromLevelOrder(new Integer[]{-10, 9, 20, null, null, 15, 7})));
    }

    static int max = Integer.MIN_VALUE;

    private static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        mps(root);
        return max;
    }

    private static int mps(TreeNode node) {
        if (node == null) return 0;
        final int value = node.val;
        final int left = mps(node.left);
        final int right = mps(node.right);
        int maxSubPath = max(
            value,
            left + value,
            right + value
        );

        int localMaxPath = Math.max(left + right + value, maxSubPath);
        if (max < localMaxPath) max = localMaxPath;
        return maxSubPath;
    }

    static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    static class TreeNode {
        final int val;
        TreeNode left;
        TreeNode right;
        TreeNode next = null;

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

    @SuppressWarnings("Duplicates")
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

//        while (!parents.isEmpty()) {
//            if (left) parents.peek().left = null;
//            else parents.poll().right = null;
//            left = !left;
//        }

        return head;
    }
}
