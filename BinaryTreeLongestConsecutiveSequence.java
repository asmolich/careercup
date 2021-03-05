import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 298. Binary Tree Longest Consecutive Sequence
 * https://lefttree.gitbooks.io/leetcode-categories/content/BinaryTree/recursion/btLongestConsecutiveSeq.html
 * #Medium
 */
public class BinaryTreeLongestConsecutiveSequence {
    public static void main(String[] args) {
        BinaryTreeLongestConsecutiveSequence sol = new BinaryTreeLongestConsecutiveSequence();
        System.out.println(sol.longestConsecutive(buildBstFromLevelOrder(new Integer[]{1, null, 3, 2, 4, null, null, null, 5}))); // 3
        System.out.println(sol.longestConsecutive(buildBstFromLevelOrder(new Integer[]{2, null, 3, 2, null, 1, null}))); // 2
    }

    private int max = 0;

    public int longestConsecutive(TreeNode root) {
        max = 0;
        if (root == null) return 0;
        longestConsecutive0(root, root.val, 1);
        return max;
    }

    private void longestConsecutive0(TreeNode node, int val, int len) {
        if (node.val == val + 1) {
            len++;
        } else {
            len = 1;
        }
        max = Math.max(max, len);
        if (node.left != null) longestConsecutive0(node.left, node.val, len);
        if (node.right != null) longestConsecutive0(node.right, node.val, len);
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
