import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode
 * 515. Find Largest Value in Each Tree Row
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 * #Medium #Tree
 */
public class FindLargestValueInEachTreeRow {

    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        FindLargestValueInEachTreeRow sol = new FindLargestValueInEachTreeRow();
        System.out.println(sol.largestValues(buildBstFromLevelOrder(new Integer[]{1, 3, 2, 5, 3, null, 9}))); // [1,3,9]
        System.out.println(sol.largestValues(buildBstFromLevelOrder(new Integer[]{1, 2, 3}))); // [1,3]
        System.out.println(sol.largestValues(buildBstFromLevelOrder(new Integer[]{1}))); // [1]
        System.out.println(sol.largestValues(buildBstFromLevelOrder(new Integer[]{1, null, 2}))); // [1,2]
        System.out.println(sol.largestValues(buildBstFromLevelOrder(new Integer[]{}))); // []
        System.out.println(sol.largestValues(buildBstFromLevelOrder(new Integer[]{0, -1}))); // [0,-1]
    }

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Map<Integer, Integer> maxPerRow = new HashMap<>();
        largestValues0(root, 0, maxPerRow);
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (maxPerRow.containsKey(i)) {
            res.add(maxPerRow.get(i));
            i++;
        }
        return res;
    }

    private void largestValues0(TreeNode node, int level, Map<Integer, Integer> maxPerRow) {
        if (node == null) return;
        maxPerRow.put(level, Math.max(node.val, maxPerRow.getOrDefault(level, Integer.MIN_VALUE)));
        largestValues0(node.left, level + 1, maxPerRow);
        largestValues0(node.right, level + 1, maxPerRow);
    }

    private static TreeNode buildBstFromLevelOrder(Integer[] order) {
        if (order == null || order.length == 0) return null;
        TreeNode head = null;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        TreeNode node;
        boolean left = true;
        for (Integer val : order) {
            node = val == null ? null : new TreeNode(val);
            if (head == null) {
                head = node;
                if (head == null) return null;
            }
            if (!nodes.isEmpty()) {
                if (left) nodes.peek().left = node;
                else nodes.poll().right = node;
                left = !left;
            }
            if (node != null) {
                nodes.add(node);
            }
        }

        return head;
    }
}
