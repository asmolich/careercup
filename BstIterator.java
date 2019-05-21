import java.util.ArrayDeque;
import java.util.Queue;

public class BstIterator {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private TreeNode current = null;
    private TreeNode pred = null;
    private TreeNode root = null;

    public BstIterator(TreeNode root) {
        this.root = root;
        current = root;
        pred = root;
    }

    /**
     * @return whether we have a current smallest number
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * @return the current smallest number
     */
    public int next() {
        int result = current.val;
        return result;
    }

    @SuppressWarnings("Duplicates")
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

        while (!parents.isEmpty()) {
            if (left) parents.peek().left = null;
            else parents.poll().right = null;
            left = !left;
        }

        return head;
    }

    private static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node);
            System.out.print(", ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = buildBstFromLevelOrder(new Integer[]{4, 2, 6, 1, 3, null, 7});

        System.out.print("In Order = ");
        inOrder(root);
        System.out.println();

        BstIterator i = new BstIterator(root);
        while (i.hasNext()) {
            System.out.print(i.next() + ", ");
        }
        System.out.println();
    }
}