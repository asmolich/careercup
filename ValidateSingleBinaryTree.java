import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode
 * https://leetcode.com/discuss/interview-question/347374/
 * #Tree
 */
public class ValidateSingleBinaryTree {
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ValidateSingleBinaryTree sol = new ValidateSingleBinaryTree();
        /*
         * Example 1:
         *
         * Let's say we have the following binary tree
         *
         * 	   	   1
         *        ↙ ↘
         *       2   3
         *          ↙
         *         4
         *
         * We can create it like this
         * TreeNode n1 = new TreeNode(1);
         * TreeNode n2 = new TreeNode(2);
         * TreeNode n3 = new TreeNode(3);
         * TreeNode n4 = new TreeNode(4);
         *
         * n1.left = n2;
         * n1.right = n3;
         * n3.left = n4;
         *
         * Input: [n4, n2, n3, n1]
         * Output: true
         */
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;

        System.out.println(sol.isBinaryTree(Arrays.asList(n4, n2, n3, n1))); // true

        /* Example 2:
         *
         * 		   1
         *        ↙  ↘
         *       2    3
         *        ↘  ↙
         *         4
         *
         * TreeNode n1 = new TreeNode(1);
         * TreeNode n2 = new TreeNode(2);
         * TreeNode n3 = new TreeNode(3);
         * TreeNode n4 = new TreeNode(4);
         *
         * n1.left = n2;
         * n1.right = n3;
         * n2.right = n4;
         * n3.left = n4;
         *
         * Input: [n2, n3, n4, n1]
         * Output: false
         */
        n1 = new TreeNode(1);
        n2 = new TreeNode(2);
        n3 = new TreeNode(3);
        n4 = new TreeNode(4);

        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.left = n4;

        System.out.println(sol.isBinaryTree(Arrays.asList(n2, n3, n4, n1))); // false

        /* Example 3:
         *
         * 	  1
         * 	⤤ ⤦
         * 	 2
         *
         * TreeNode n1 = new TreeNode(1);
         * TreeNode n2 = new TreeNode(2);
         *
         * n1.left = n2;
         * n2.left = n1;
         *
         * Input: [n1, n2]
         * Output: false
         */
        n1 = new TreeNode(1);
        n2 = new TreeNode(2);

        n1.left = n2;
        n2.left = n1;

        System.out.println(sol.isBinaryTree(Arrays.asList(n1, n2))); // false

        /* Example 4:
         *
         * 	       1          4
         *        ↙ ↘        ↙  ↘
         *       2   3      5     6
         *
         * TreeNode n1 = new TreeNode(1);
         * TreeNode n2 = new TreeNode(2);
         * TreeNode n3 = new TreeNode(3);
         * TreeNode n4 = new TreeNode(4);
         * TreeNode n5 = new TreeNode(5);
         * TreeNode n6 = new TreeNode(6);
         *
         * n1.left = n2;
         * n1.right = n3;
         *
         * n4.left = n5;
         * n4.right = n6;
         *
         * Input: [n2, n6, n4, n1, n3, n5]
         * Output: false
         */
        n1 = new TreeNode(1);
        n2 = new TreeNode(2);
        n3 = new TreeNode(3);
        n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.left = n3;

        n4.left = n5;
        n4.left = n6;

        System.out.println(sol.isBinaryTree(Arrays.asList(n2, n6, n4, n1, n3, n5))); // false

        /* Example 5:
         *
         * 	       1
         *        ↙ ↘
         *       2   3
         *          ↙
         *         4
         *
         * TreeNode n1 = new TreeNode(1);
         * TreeNode n2 = new TreeNode(2);
         * TreeNode n3 = new TreeNode(3);
         * TreeNode n4 = new TreeNode(4);
         *
         * n1.left = n2;
         * n1.right = n3;
         * n3.left = n4;
         *
         * Input: [n2, n3, n1]
         * Output: false
         * Explanation: l4 is a part of the tree but it's missing in the input list so return false.
         */
        n1 = new TreeNode(1);
        n2 = new TreeNode(2);
        n3 = new TreeNode(3);
        n4 = new TreeNode(4);

        n1.left = n2;
        n1.left = n3;
        n3.left = n4;

        System.out.println(sol.isBinaryTree(Arrays.asList(n2, n3, n1))); // false
    }

    public boolean isBinaryTree(List<TreeNode> nodes) {
        if (nodes == null || nodes.isEmpty()) return false;

        Map<TreeNode, Integer> inDegree = new HashMap<>();
        for (TreeNode node : nodes) {
            if (node.left != null) inDegree.put(node.left, inDegree.getOrDefault(node.left, 0) + 1);
            if (node.right != null) inDegree.put(node.right, inDegree.getOrDefault(node.right, 0) + 1);
        }
        TreeNode root = null;
        for (TreeNode node : nodes) {
            if (inDegree.containsKey(node)) {
                if (inDegree.get(node) != 1) return false;
            } else {
                if (root == null) root = node;
                else return false;
            }
        }

        return root != null && nodes.size() == (inDegree.size() + 1);
    }
}
