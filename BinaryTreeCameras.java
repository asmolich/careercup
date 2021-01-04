import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 968. Binary Tree Cameras
 * https://leetcode.com/problems/binary-tree-cameras/
 * #Hard #Tree #DP
 */
public class BinaryTreeCameras {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return hashCode() + "{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        Integer[][] data = new Integer[][]{
                {0, 0, null, 0, 0}, //1
                {0, 0, null, 0, null, 0, null, null, 0}, //2
                {0, 0, null, 0, null, 0, null, 0, null, 0, null}, //2
                {0, 0, 0},  //1
                {0, 0, 0, 0, null}, //2
                {0, 0, 0, 0, null, null, 0}, //2
                {0, 0, 0, 0, null, null, 0, null, 0}, //2
                {0, 0, 0, 0, null, null, 0, null, null, null, 0}, //2
                {0, 0, 0, 0, 0, null, 0, null, 0, null, 0, null, null, null, 0}, //3

                {0, null, 0, null, 0, 0, 0}, //2
                {0, null, 0, null, 0, null, 0, null, 0, 0, 0}, //2
                {0, null, 0, null, 0, null, 0, null, 0, 0, 0, null, null, 0, 0}, //3
                {0, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, 0}, //3

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, null, 0, 0, null, 0, null, 0, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, null, 0, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, null, 0, 0, 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, null, null, 0, 0, null, 0, null, 0, null, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, null, 0, 0, null, null, 0, null, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, null, 0, null, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, null, null, null, 0, null, null, 0, null, null, null, null, 0, null, 0, null, 0, 0, 0, null, null, 0, null, null, null, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, null, 0, 0, 0, null, 0, 0, null, 0, null, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, 0, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, null, null, null, 0, 0, null, null, 0, null, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, null, 0, null, null, null, null, null, null, null, 0, 0, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, 0, null, null, 0, null, null, null, null, null, null, 0, 0, null, null, 0, null, null, null, null, null, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, 0, 0, null, 0, null, null, 0, 0, null, null, null, 0, 0, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, 0, null, null, null, 0, 0, 0, null, 0, null, null, null, 0, null, 0, 0, null, null, null, 0, null, null, 0, null, null, 0, 0, 0, null, 0, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, null, 0, null, null, 0, null, null, 0, 0, null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, null, 0, null, null, 0, null, 0, null, 0, null, 0, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, null, 0, 0, null, 0, 0, 0, 0, null, null, null, null, null, 0, null, null, 0, null, 0, null, null, null, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, null, null, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, 0, null, null, 0, 0, null, null, null, null, 0, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, null, 0, null, 0, null, 0, 0, null, 0, null, null, 0, null, 0, null, 0, null, null, 0, 0, null, 0, null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, null, null, null, null, null, null, 0, null, 0, null, 0, null, null, null, 0, null, null, null, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, null, 0, 0, null, null, null, null, null, null, 0, null, 0, null, null, null, 0, 0, 0, null, 0, 0, null, 0, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, null, null, null, null, null, null, null, 0, null, null, 0, null, 0, 0, null, null, 0, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, 0, null, 0, 0, 0, null, null, null, null, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, null, 0, null, null, null, null, null, 0, null, null, null, null, null, null, 0, 0, null, 0, null, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, 0, null, null, null, null, 0, 0, null, null, 0, null, 0, null, 0, null, null, 0, null, null, null, null, 0, null, 0, null, null, null, null, 0, 0, null, 0, 0, 0, null, 0, 0, 0, null, 0, null, null, null, null, 0, null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, 0, null, 0, null, 0, null, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, 0, 0, null, null, 0, null, null, 0, null, 0, 0, null, 0, 0, 0, null, 0, 0, null, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, null, 0, null, null, null, 0, 0, null, null, null, 0, 0, null, 0, null, null, 0, null, null, 0, 0, null, null, null, 0, 0, null, null, 0, null, 0, null, 0, 0, null, null, null, 0, 0, null, null, null, null, 0, 0, null, 0, null, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, 0, null, null, null, null, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0, null, 0, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0, null, null, 0, 0, null, 0, 0, 0, null, 0, null, null, null, 0, null, 0, null, null, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, null, null, null, null, null, 0, 0, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, null, null, null, 0, null, null, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, null, null, 0, 0, 0, null, 0, null, 0, null, 0, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, 0, 0, null, null, null, null, null, 0, null, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, null, null, 0, null, null, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, null, 0, null, 0, null, null, null, null, null, 0, null, null, null, null, null, 0, 0, 0, 0, null, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, null, null, null, null, null, null, null, null, 0, null, null, null, 0, null, 0, null, 0, 0, null, null, 0, 0, null, 0, 0, 0, null, 0, 0, 0, null, 0, null, null, null, null, 0, null, 0, null, 0, null, null, null, null, null, null, 0, 0, 0, null, null, 0, null, null, null, 0, 0, null, null, null, null, 0, null, 0, null, null, null, 0, null, 0, 0, 0, 0, 0, 0, null, 0, 0, null, null, null, 0, null, 0, null, null, 0, null, null, null, null, null, null, null, null, null, null, 0, null, 0, 0, null, null, null, null, 0, null, 0, null, null, null, null, 0, 0, null, null, 0, null, null, 0, 0, 0, 0, null, 0, null, null, 0, null, null, null, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, null, null, null, null, null, 0, null, 0, null, 0, null, null, 0, null, null, null, null, null, null, null, null, null, 0, null, 0, 0, 0, null, null, null, null, 0, null, null, null, 0, null, null, null, null, 0, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null, 0, null, null, null, null, null, 0, null, 0, 0, 0, 0, null, null, null, 0, 0, 0, null, 0, 0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, null, 0, 0, null, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, null, null, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, 0, null, 0, null, null, null, null, 0, 0, 0, null, null, null, 0, null, null, 0, 0, null, null, 0, 0, null, null, null, null, null, null, null, 0, null, 0, null, null, null, null, 0, 0, null, null, 0, 0, 0, 0, 0, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, 0, 0, null, 0, null, null, 0, null, 0, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, 0, null, 0, null, null, 0, null, null, 0, 0, null, null, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, null, null, null, null, null, null, null, null, 0, 0, 0, null, null, null, null, 0, null, 0, null, 0, null, null, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, null, 0, null, null, null, 0, null, null, 0, 0, null, null, null, null, 0, null, null, null, null, null, 0, 0, null, null, null, null, null, null, 0, 0, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, 0, 0, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0}
        };
        long start = System.currentTimeMillis();
        for (Integer[] tree : data) {
            var root = buildBstFromLevelOrder(tree);
            //System.out.println(root);
            System.out.println(new BinaryTreeCameras().minCameraCover(root));
        }
        System.out.println(Duration.of(System.currentTimeMillis() - start, ChronoUnit.MILLIS).toSeconds());
    }

    enum State {
        NONE, COVERED, CAMERA
    }

    private int count = 0;

    private int minCameraCover(TreeNode root) {
        if (minCameraCover0(root) == State.NONE) count++;
        return count;
    }

    private State minCameraCover0(TreeNode node) {
        if (node == null) return State.COVERED;
        State left = minCameraCover0(node.left);
        State right = minCameraCover0(node.right);
        if (left == State.NONE || right == State.NONE) {
            count++;
            return State.CAMERA;
        }
        if (left == State.CAMERA || right == State.CAMERA)
            return State.COVERED;
        return State.NONE;
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
