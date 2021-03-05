import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 538. Convert BST to Greater Tree
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * 1038. Binary Search Tree to Greater Sum Tree
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/submissions/
 * #Medium #Tree
 */
public class ConvertBstToGreaterTree {
    public static void main(String[] args) {
        ConvertBstToGreaterTree sol = new ConvertBstToGreaterTree();
        System.out.println(sol.convertBST(buildBstFromLevelOrder(new Integer[]{4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8}))); // [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
        System.out.println(sol.convertBST(buildBstFromLevelOrder(new Integer[]{0, null, 1}))); // [1,null,1]
        System.out.println(sol.convertBST(buildBstFromLevelOrder(new Integer[]{1, 0, 2}))); // [3,3,2]
        System.out.println(sol.convertBST(buildBstFromLevelOrder(new Integer[]{3, 2, 4, 1}))); // [7,9,4,10]
    }

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST0(root);
        return root;
    }

    private void convertBST0(TreeNode node) {
        if (node == null) return;
        convertBST0(node.right);
        sum += node.val;
        node.val = sum;
        convertBST0(node.left);
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
            return "" + val + ", l=" + left + ", r=" + right;
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
