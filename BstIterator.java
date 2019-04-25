import java.util.ArrayDeque;
import java.util.Deque;
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

    private TreeNode next;
    private Deque<TreeNode> deq = new ArrayDeque<>();

    public BstIterator(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            deq.addLast(node);
            next = node;
            node = node.left;
        }

//        System.out.println("Init: deq = " + deq + ", next = " + next);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
//        System.out.println("deq = " + deq + ", next = " + next);
        return next.right != null || !deq.isEmpty();
    }

    /**
     * @return the next smallest numberPurchaseProductMoneyRequestMessage
     */
    public int next() {
        //System.out.println(next);
        if (next.right == null) {
            next = deq.removeLast();
        } else {
            TreeNode node = next.right;
            while (node != null) {
                deq.add(node);
                node = node.left;
            }
            if (!deq.isEmpty())
                next = deq.removeLast();
        }
        return next.val;
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

    private static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node);
            System.out.print(", ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = buildBstFromLevelOrder(new Integer[]{4, 2, 6, 1, 3, null, 7, null, null, null, null, null, null});

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
