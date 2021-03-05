import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode
 * 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * #Medium #Tree #BFS
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        BinaryTreeRightSideView sol = new BinaryTreeRightSideView();
        System.out.println(sol.rightSideView(buildBstFromLevelOrder(new Integer[]{1, 2, 3, null, 5, null, 4}))); // [1, 3, 4]
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<TreeNode> deq = new ArrayDeque<>();
        deq.add(root);
        List<Integer> res = new ArrayList<>();
        while (!deq.isEmpty()) {
            int size = deq.size();
            Integer right = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = deq.poll();
                if (node == null) continue;
                if (right == null) right = node.val;
                if (node.right != null) deq.add(node.right);
                if (node.left != null) deq.add(node.left);
            }
            res.add(right);
        }
        return res;
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
            return String.valueOf(val);
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
