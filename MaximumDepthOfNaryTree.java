import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode
 * 559. Maximum Depth of N-ary Tree
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaximumDepthOfNaryTree {
    public static void main(String[] args) {
        MaximumDepthOfNaryTree sol = new MaximumDepthOfNaryTree();
        TreeNode root = new TreeNode(1, Arrays.asList(
                new TreeNode(3, Arrays.asList(new TreeNode(5), new TreeNode(6))),
                new TreeNode(2),
                new TreeNode(4)
        ));
        System.out.println(sol.maxDepth(root)); // 3
        //noinspection ArraysAsListWithZeroOrOneArgument
        root = new TreeNode(1, Arrays.asList(
                new TreeNode(2),
                new TreeNode(3, Arrays.asList(new TreeNode(6), new TreeNode(7, Arrays.asList(new TreeNode(11, Arrays.asList(new TreeNode(14))))))),
                new TreeNode(4, Arrays.asList(new TreeNode(8, Arrays.asList(new TreeNode(12))))),
                new TreeNode(5, Arrays.asList(new TreeNode(9, Arrays.asList(new TreeNode(6))), new TreeNode(10)))
        ));
        System.out.println(sol.maxDepth(root)); // 5
    }

    private static class TreeNode {
        public int val;
        public List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }

        public TreeNode(int val, List<TreeNode> children) {
            this.val = val;
            this.children = children;
        }
    }

    public int maxDepth(TreeNode node) {
        if (node == null) return 0;

        int max = 0;
        for (TreeNode child : node.children) {
            max = Math.max(max, maxDepth(child));
        }
        return 1 + max;
    }
}
