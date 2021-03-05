import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 669. Trim a Binary Search Tree
 * https://leetcode.com/problems/trim-a-binary-search-tree/
 * #Medium #Tree
 */
public class TrimABinarySearchTree {
    public static void main(String[] args) {
        TrimABinarySearchTree sol = new TrimABinarySearchTree();
        System.out.println(sol.trimBST(buildBstFromLevelOrder(new Integer[]{1, 0, 2}), 1, 2)); // [1,null,2]
        System.out.println(sol.trimBST(buildBstFromLevelOrder(new Integer[]{3, 0, 4, null, 2, null, null, 1}), 1, 3)); // [3,2,null,1]
        System.out.println(sol.trimBST(buildBstFromLevelOrder(new Integer[]{1}), 1, 2)); // [1]
        System.out.println(sol.trimBST(buildBstFromLevelOrder(new Integer[]{1, null, 2}), 1, 3)); // [1,null,2]
        System.out.println(sol.trimBST(buildBstFromLevelOrder(new Integer[]{1, null, 2}), 2, 4)); // [2]
        System.out.println(sol.trimBST(buildBstFromLevelOrder(new Integer[]{100, 50, 150, 25, 75, 125, 175, 12, 37, 62, 87, 112, 137, 162, 187}), 113, 162)); // [150,125,162,null,137]
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        return trimBST0(root, null, low, high);
    }

    private TreeNode trimBST0(TreeNode node, TreeNode parent, int low, int high) {
        if (node == null) return parent;
        if (node.val >= low && node.val <= high) {
            trimBST0(node.left, node, low, high);
            trimBST0(node.right, node, low, high);
            return node;
        } else if (node.val < low) {
            if (parent != null) {
                parent.left = node.right;
            }
            return trimBST0(node.right, parent, low, high);
        } else {
            if (parent != null) {
                parent.right = node.left;
            }
            return trimBST0(node.left, parent, low, high);
        }
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
