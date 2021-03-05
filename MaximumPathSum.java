import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 124. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class MaximumPathSum {
    public static void main(String[] args) {
        MaximumPathSum sol = new MaximumPathSum();
        System.out.println(sol.maxPathSum(buildBstFromLevelOrder(new Integer[]{4, 2, 6, 1, 3, null, 7}))); // 22
        System.out.println(sol.maxPathSum(buildBstFromLevelOrder(new Integer[]{1, 2, 3}))); // 6
        System.out.println(sol.maxPathSum(buildBstFromLevelOrder(new Integer[]{-10, 9, 20, null, null, 15, 7}))); // 42
    }

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        if (root == null) return 0;
        maxPathSum0(root);
        return max;
    }

    private int maxPathSum0(TreeNode node) {
        if (node == null) return 0;
        int value = node.val;
        int left = maxPathSum0(node.left);
        int right = maxPathSum0(node.right);
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
