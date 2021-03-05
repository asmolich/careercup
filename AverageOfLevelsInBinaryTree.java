import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode
 * 637. Average of Levels in Binary Tree
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * #Easy #Tree #BFS
 */
public class AverageOfLevelsInBinaryTree {
    public static void main(String[] args) {
        AverageOfLevelsInBinaryTree sol = new AverageOfLevelsInBinaryTree();
        System.out.println(sol.averageOfLevels(buildBstFromLevelOrder(new Integer[]{3, 9, 20, null, null, 15, 7}))); // [3, 14.5, 11]
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Double> res = new ArrayList<>();
        Deque<TreeNode> deq = new ArrayDeque<>();
        deq.add(root);
        while (!deq.isEmpty()) {
            int size = deq.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = deq.poll();
                if (node == null) continue;
                ;
                sum += node.val;
                if (node.left != null) deq.add(node.left);
                if (node.right != null) deq.add(node.right);
            }
            res.add((double) sum / size);
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
