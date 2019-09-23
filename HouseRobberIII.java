import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode. 337. House Robber III
 * https://leetcode.com/problems/house-robber-iii/
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        System.out.println(rob(buildBstFromLevelOrder(new Integer[]{1})));
        System.out.println(rob(buildBstFromLevelOrder(new Integer[]{3, 4, 5, 1, 3, null, 1})));
        System.out.println(rob(buildBstFromLevelOrder(new Integer[]{3, 2, 3, null, 3, null, 1}))); // 7
        System.out.println(rob(buildBstFromLevelOrder(new Integer[]{3, 4, 5, 1, 3, null, 1}))); // 9
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class State {
        int max = 0;
        int maxPrev = 0;

        int max() {
            return Math.max(max, maxPrev);
        }
    }

    private static int rob(TreeNode root) {
        if (root == null) return 0;

        State result = rob0(root);

        return result.max();
    }

    private static State rob0(TreeNode node) {
        if (node == null) return new State();

        State left = rob0(node.left);
        State right = rob0(node.right);


        /*
         *     1  4,2
         *    /
         *   2  2,3
         *  /
         * 3  3,0
         */

        int robbedLeft = left.maxPrev;
        int robbedLeftPrev = left.max();
        int robbedRight = right.maxPrev;
        int robbedRightPrev = right.max();

        int maxRobbed = node.val + robbedLeft + robbedRight;
        int maxRobbedPrev = robbedLeftPrev + robbedRightPrev;

        State state = new State();
        state.max = maxRobbed;
        state.maxPrev = maxRobbedPrev;
        return state;
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
