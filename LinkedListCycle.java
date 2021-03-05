/**
 * LeetCode
 * 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 * #Easy #LinkedList #Cycle
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        LinkedListCycle sol = new LinkedListCycle();
        ListNode node = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4))));
        node.next.next.next.next = node.next;
        System.out.println(sol.hasCycle(node)); // true
        node = new ListNode(3);
        System.out.println(sol.hasCycle(node)); // false
        node = new ListNode(3);
        node.next = node;
        System.out.println(sol.hasCycle(node)); // true
        node = new ListNode(1, new ListNode(2));
        node.next.next = node;
        System.out.println(sol.hasCycle(node)); // true
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode p1 = head;
        ListNode p2 = head;
        do {
            p1 = p1.next;
            if (p2.next == null) return false;
            p2 = p2.next.next;
//            System.out.println("p1 = " + p1 + ", p2 = " + p2);
        } while (p2 != null && p1 != p2);
        return p1 == p2;
    }

    private static class ListNode {
        ListNode next = null;
        final int val;

        ListNode(int v) {
            val = v;
        }

        ListNode(int v, ListNode n) {
            val = v;
            next = n;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }
}
