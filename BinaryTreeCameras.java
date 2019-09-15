import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 968. Binary Tree Cameras
 * https://leetcode.com/problems/binary-tree-cameras/
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
//                {0, 0, null, 0, 0}, //1
//                {0, 0, null, 0, null, 0, null, null, 0}, //2
//                {0, 0, null, 0, null, 0, null, 0, null, 0, null}, //2
//                {0, 0, 0},  //1
//                {0, 0, 0, 0, null}, //2
//                {0, 0, 0, 0, null, null, 0}, //2
//                {0, 0, 0, 0, null, null, 0, null, 0}, //2
//                {0, 0, 0, 0, null, null, 0, null, null, null, 0}, //2
//                {0, 0, 0, 0, 0, null, 0, null, 0, null, 0, null, null, null, 0}, //3
//
//                {0, null, 0, null, 0, 0, 0}, //2
//                {0, null, 0, null, 0, null, 0, null, 0, 0, 0}, //2
//                {0, null, 0, null, 0, null, 0, null, 0, 0, 0, null, null, 0, 0}, //3
//                {0, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, 0} //3

                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, null, 0, 0, null, 0, null, 0, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, null, 0, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, null, 0, 0, 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, null, null, 0, 0, null, 0, null, 0, null, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, null, 0, 0, null, null, 0, null, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, null, 0, null, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, null, null, null, 0, null, null, 0, null, null, null, null, 0, null, 0, null, 0, 0, 0, null, null, 0, null, null, null, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, null, 0, 0, 0, null, 0, 0, null, 0, null, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, 0, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, null, null, null, 0, 0, null, null, 0, null, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, null, 0, null, null, null, null, null, null, null, 0, 0, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, 0, null, null, 0, null, null, null, null, null, null, 0, 0, null, null, 0, null, null, null, null, null, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, 0, 0, null, 0, null, null, 0, 0, null, null, null, 0, 0, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, 0, null, null, null, 0, 0, 0, null, 0, null, null, null, 0, null, 0, 0, null, null, null, 0, null, null, 0, null, null, 0, 0, 0, null, 0, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, null, 0, null, null, 0, null, null, 0, 0, null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, null, 0, null, null, 0, null, 0, null, 0, null, 0, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, null, 0, 0, null, 0, 0, 0, 0, null, null, null, null, null, 0, null, null, 0, null, 0, null, null, null, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, null, null, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, 0, null, null, 0, 0, null, null, null, null, 0, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, null, 0, null, 0, null, 0, 0, null, 0, null, null, 0, null, 0, null, 0, null, null, 0, 0, null, 0, null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, null, null, null, null, null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, null, null, null, null, null, null, 0, null, 0, null, 0, null, null, null, 0, null, null, null, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, null, 0, 0, null, null, null, null, null, null, 0, null, 0, null, null, null, 0, 0, 0, null, 0, 0, null, 0, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, null, null, null, null, null, null, null, 0, null, null, 0, null, 0, 0, null, null, 0, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, 0, 0, null, 0, 0, 0, null, null, null, null, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, null, 0, null, null, null, null, null, 0, null, null, null, null, null, null, 0, 0, null, 0, null, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, 0, null, null, null, null, 0, 0, null, null, 0, null, 0, null, 0, null, null, 0, null, null, null, null, 0, null, 0, null, null, null, null, 0, 0, null, 0, 0, 0, null, 0, 0, 0, null, 0, null, null, null, null, 0, null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, 0, null, 0, null, 0, null, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, 0, 0, null, null, 0, null, null, 0, null, 0, 0, null, 0, 0, 0, null, 0, 0, null, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, null, 0, null, null, null, 0, 0, null, null, null, 0, 0, null, 0, null, null, 0, null, null, 0, 0, null, null, null, 0, 0, null, null, 0, null, 0, null, 0, 0, null, null, null, 0, 0, null, null, null, null, 0, 0, null, 0, null, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, 0, null, null, null, null, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0, null, 0, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0, null, null, 0, 0, null, 0, 0, 0, null, 0, null, null, null, 0, null, 0, null, null, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0, null, null, null, null, null, 0, 0, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, null, null, null, 0, null, null, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, null, null, 0, 0, 0, null, 0, null, 0, null, 0, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, 0, 0, null, null, null, null, null, 0, null, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, null, null, 0, null, null, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, null, 0, null, 0, null, null, null, null, null, 0, null, null, null, null, null, 0, 0, 0, 0, null, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, null, null, null, null, null, null, null, null, 0, null, null, null, 0, null, 0, null, 0, 0, null, null, 0, 0, null, 0, 0, 0, null, 0, 0, 0, null, 0, null, null, null, null, 0, null, 0, null, 0, null, null, null, null, null, null, 0, 0, 0, null, null, 0, null, null, null, 0, 0, null, null, null, null, 0, null, 0, null, null, null, 0, null, 0, 0, 0, 0, 0, 0, null, 0, 0, null, null, null, 0, null, 0, null, null, 0, null, null, null, null, null, null, null, null, null, null, 0, null, 0, 0, null, null, null, null, 0, null, 0, null, null, null, null, 0, 0, null, null, 0, null, null, 0, 0, 0, 0, null, 0, null, null, 0, null, null, null, 0, null, 0, 0, 0, null, 0, 0, 0, 0, 0, null, null, null, null, null, 0, null, 0, null, 0, null, null, 0, null, null, null, null, null, null, null, null, null, 0, null, 0, 0, 0, null, null, null, null, 0, null, null, null, 0, null, null, null, null, 0, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null, 0, null, null, null, null, null, 0, null, 0, 0, 0, 0, null, null, null, 0, 0, 0, null, 0, 0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, null, 0, 0, null, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, null, 0, 0, null, null, null, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, 0, null, 0, null, null, null, null, 0, 0, 0, null, null, null, 0, null, null, 0, 0, null, null, 0, 0, null, null, null, null, null, null, null, 0, null, 0, null, null, null, null, 0, 0, null, null, 0, 0, 0, 0, 0, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, 0, 0, null, 0, null, null, 0, null, 0, null, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, 0, null, 0, null, null, 0, null, null, 0, 0, null, null, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0, 0, null, null, null, null, null, null, null, null, 0, 0, 0, null, null, null, null, 0, null, 0, null, 0, null, null, null, null, 0, 0, null, null, null, null, null, 0, 0, 0, null, 0, null, null, null, 0, null, null, 0, 0, null, null, null, null, 0, null, null, null, null, null, 0, 0, null, null, null, null, null, null, 0, 0, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, 0, 0, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0}
        };
        long start = System.currentTimeMillis();
        for (Integer[] tree : data) {
            var root = buildBstFromLevelOrder(tree);
            //System.out.println(root);
            System.out.println(minCameraCover(root));
        }
        System.out.println(Duration.of(System.currentTimeMillis() - start, ChronoUnit.MILLIS).toSeconds());
    }

    /*
     * 2D:
     *    0     1     2     3     4     5
     * 0 ( ) - ( ) - ( ) - ( ) - (c) - ( )
     *    |     |     |     |     |     |
     * 1 ( ) - (c) - ( ) - ( ) - ( ) - ( )
     *    |     |     |     |     |     |
     * 2 ( ) - ( ) - ( ) - ( ) - (c) - ( )
     *    |     |     |     |     |     |
     * 3 ( ) - (c) - ( ) - ( ) -- X -- ( )
     *
     * state:
     *  - min # of cameras in subtree               (a)           0*                  (a)                  0
     *  - place camera in node Yes/No               /           /   \*                / \                 / \
     *  - placed camera in a child Yes/No         (b)*        a       b*            (d)  (b)*           a+0 a-0
     *                                            /         /   \    / \*           / \    \            /     \
     *     (a)        [0]                       (c)       c      d  d   e*       (g)  (e)* (c)    [(d),(b)]    ...
     *     /        /     \                     /       /   \   /  /               \    \           /  |
     *   (b)*     a+0     a-0                 (d)      e     f f  f                 (h)* (f)      b-b d-d
     *   / \       |       |                  /                                       \           /    |
     * (c) (d)    b-b     b+0               (e)*                                      (i)        c+  [e,g]d
     *   \        /         \               /                                                         / \
     *   (e)*  [с,d]b    [(c)c,(d)d]      (f)            (a)*         0                             e+d g+d
     *         /  \        /  \                          / \         / \                            /    |
     *        c    d+b   c-c  d-d                      (b) (c)     a+0 a-0                        f-f   h-h
     *       / \          |                                        /     \                               |
     *     c+b c-b       e+c                                 [(b)b,(c)c] [b,c]                          i+h
     *      |   |                                              /    \     /  \
     *     e-e e+b                                            b-b  c-c   b+0 c+0
     *
     * placed camera or not
     * # of cameras
     * last node under camera
     */
    private static int minCameraCover(TreeNode root) {
        if (root == null) return 0;

        return Math.min(
                minCameraCover0(root, false, true, 0),
                minCameraCover0(root, false, false, 0));
    }

    private static int minCameraCover0(TreeNode node, boolean parentHasCam, boolean parentUnderCam, int numberOfCams) {
        if (node == null) return numberOfCams;

        if (node.left != null && node.right != null) {
//            System.out.println(node.hashCode());
            if (parentUnderCam || parentHasCam) {
                // place camera here or in one of children
                int ifPlacedHere = numberOfCams + 1 +
                        minCameraCover0(node.left, true, false, 0) +
                        minCameraCover0(node.right, true, false, 0);

                int ifPlacedInLeft = numberOfCams +
                        minCameraCover0(node.left, false, false, 0) +
                        minCameraCover0(node.right, false, true, 0);

                int ifPlacedInRight = numberOfCams +
                        minCameraCover0(node.left, false, true, 0) +
                        minCameraCover0(node.right, false, false, 0);

//                System.out.println("ifPlacedHere=" + ifPlacedHere);
//                System.out.println("ifPlacedInLeft=" + ifPlacedInLeft);
//                System.out.println("ifPlacedInRight=" + ifPlacedInRight);
                int min = Math.min(ifPlacedHere, Math.min(ifPlacedInLeft, ifPlacedInRight));
                if (!parentHasCam) return min;
                return Math.min(min, numberOfCams +
                        minCameraCover0(node.left, false, true, 0) +
                        minCameraCover0(node.right, false, true, 0));
            }
            return numberOfCams + 1 +
                    minCameraCover0(node.left, true, false, 0) +
                    minCameraCover0(node.right, true, false, 0);
        } else {
            TreeNode next = node.left != null ? node.left : node.right;
            boolean mustPlaceHere = !parentHasCam && !parentUnderCam || (next == null && !parentHasCam);
//            if (mustPlaceHere) {
//                System.out.println(node.hashCode());
//            }
            int newNumberOfCams = mustPlaceHere ? numberOfCams + 1 : numberOfCams;
            if (next == null) return newNumberOfCams;
            return minCameraCover0(next, mustPlaceHere, parentHasCam, newNumberOfCams);
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
