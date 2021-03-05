/**
 * LeetCode
 * 86. Partition List
 * https://leetcode.com/problems/partition-list/
 * #Medium #LinkedList
 */
public class PartitionList {
    public static void main(String[] args) {
        PartitionList sol = new PartitionList();
        System.out.println(sol.partition(new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2)))))), 3)); // [1,2,2,4,3,5]
        System.out.println(sol.partition(new ListNode(2, new ListNode(1)), 2)); // [1,2]
        System.out.println(sol.partition(new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))))))), 4)); // [1,2,3,5,6,7,8,4]
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode node = head;
        ListNode tail = null;
        ListNode newHead = null;
        while (node != null) {
            if (newHead == null && node.val < x) newHead = node;
            if (node.val < x) tail = node;
            node = node.next;
        }
        if (newHead == null) return head;
        ListNode tailHead = tail;
        node = head;
        while (node != newHead) {
            ListNode next = node.next;
            node.next = tail.next;
            tail.next = node;
            tail = tail.next;
            node = next;
        }
        node = newHead;
        ListNode prev = null;
        while (node != tailHead) {
            ListNode next = node.next;
            if (node.val >= x && prev != null) {
                node.next = tail.next;
                tail.next = node;
                tail = tail.next;
                prev.next = next;
            } else {
                prev = node;
            }
            node = next;
        }
        return newHead;
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
            return val + " -> " + next;
        }
    }
}
