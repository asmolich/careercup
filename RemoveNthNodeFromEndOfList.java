/**
 * LeetCode
 * 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * #Medium
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList sol = new RemoveNthNodeFromEndOfList();
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(root); // 1 -> 2 -> 3 -> 4 -> 5 -> null
        System.out.println(sol.removeNthFromEnd(root, 2)); // 1 -> 2 -> 3 -> 5 -> null
        root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(sol.removeNthFromEnd(root, 5)); // 2 -> 3 -> 4 -> 5 -> null
        root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(sol.removeNthFromEnd(root, 6)); // 1 -> 2 -> 3 -> 4 -> 5 -> null
        root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(sol.removeNthFromEnd(root, 1)); // 1 -> 2 -> 3 -> 4 -> null
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode node = head;
        while (node != null && n > 0) {
            node = node.next;
            n--;
        }
        if (node == null && n > 0) return head;

        ListNode node2 = head;
        ListNode prev = null;
        while (node != null) {
            prev = node2;
            node2 = node2.next;
            node = node.next;
        }
        if (prev != null) {
            prev.next = node2.next;
            return head;
        }
        return head.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "" + val + " -> " + next;
        }
    }
}
