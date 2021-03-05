/**
 * LeetCode
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * #Easy #LinkedList
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists sol = new IntersectionOfTwoLinkedLists();
        ListNode headC = new ListNode(8, new ListNode(4, new ListNode(5)));
        ListNode headA = new ListNode(4, new ListNode(1, headC));
        ListNode headB = new ListNode(5, new ListNode(6, new ListNode(1, headC)));
        System.out.println(sol.getIntersectionNode(headA, headB));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lengthA = 0;
        ListNode node = headA;
        while (node != null) {
            lengthA++;
            node = node.next;
        }
        int lengthB = 0;
        node = headB;
        while (node != null) {
            lengthB++;
            node = node.next;
        }

        ListNode shorter;
        ListNode longer;
        int shorterLen;
        int longerLen;
        if (lengthA <= lengthB) {
            shorter = headA;
            longer = headB;
            shorterLen = lengthA;
            longerLen = lengthB;
        } else {
            shorter = headB;
            longer = headA;
            shorterLen = lengthB;
            longerLen = lengthA;
        }
        int diff = longerLen - shorterLen;
        while (longer != null && diff > 0) {
            longer = longer.next;
            diff--;
        }

        while (longer != null && shorter != null) {
            if (longer == shorter) return shorter;
            longer = longer.next;
            shorter = shorter.next;
        }
        return null;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode n) {
            val = x;
            next = n;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }
}
