import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * LeetCode
 * 230. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * #Medium #Stack
 */
public class KthSmallestElementInBST {
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

    public static void main(String[] args) {
        KthSmallestElementInBST sol = new KthSmallestElementInBST();
        System.out.println(sol.kthSmallest(buildBstFromLevelOrder(new Integer[]{3, 1, 4, null, 2}), 1)); // 1
        System.out.println(sol.kthSmallest(buildBstFromLevelOrder(new Integer[]{5, 3, 6, 2, 4, null, null, 1}), 3)); // 3
        System.out.println(sol.kthSmallest(buildBstFromLevelOrder(new Integer[]{1, null, 2}), 2)); // 2
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        Deque<TreeNode> deq = new ArrayDeque<>();
        TreeNode node = root;
        while (true) {
            while (node != null) {
                deq.add(node);
                node = node.left;
            }
            node = deq.removeLast();
            k--;
            if (k == 0) return node.val;
            node = node.right;
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
