public class LinkedLists {
    private static class ListNode {
        ListNode next = null;
        final int val;

        ListNode(int v) {val = v;}

        ListNode(int v, ListNode n) {
            val = v;
            next = n;
        }

        @Override
        public String toString() { return "[" + val + ']'; }
    }

    public static void main(String[] args) {
        LinkedLists lists = new LinkedLists();
        ListNode ones = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1)))));
        System.out.println(lists.toString(lists.reverseList(ones)));
        System.out.println(lists.toString(lists.deleteDuplicates2(ones)));
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        list = lists.rotateRight(list, 101);
        System.out.println(lists.toString(list));
    }

    public String toString(ListNode node) {
        StringBuilder sb = new StringBuilder("List[");
        while (node != null) {
            sb.append(node.val);
            if (node.next != null) sb.append(", ");
            node = node.next;
        }
        sb.append(']');
        return sb.toString();
    }

    public ListNode reverseList(ListNode a) {
        if (a == null) return a;
        ListNode node = a;
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode node = head;
        ListNode prev = null;
        while (node != null) {
            if (prev != null && node.val == prev.val) {
                prev.next = node.next;
            } else {
                prev = node;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return head;
        ListNode node = head;
        ListNode newHead = head;
        ListNode prev = null;
        while (node != null) {
            if (node.next != null) {
                if (node.val == node.next.val) {
                    continue;
                } else {
                    if (prev != null) prev.next = node.next;
                }
            } else {
                if (prev == null) {
                    return node;
                }
            }
            prev = node;
            node = node.next;
        }
        return newHead;
    }

    public ListNode rotateRight(ListNode a, /* 1-based */int b) {
        if (a == null || a.next == null || b < 1) return a;

        ListNode head = a;
        ListNode p1 = a;
        ListNode p1prev = a;
        ListNode p2 = a;
        ListNode p2prev = a;
        // 1. move p1 on b positions forward
        int pos = b;
        while (p1 != null && pos > 0) {
            p1 = p1.next;
            pos--;
        }
        // 2. b > a.size()
        if (pos > 0) {
            int len = b - pos;
            pos = b % len;
            if (pos == 0) return a;
            p1 = a;
            while (p1 != null && pos > 0) {
                p1 = p1.next;
                pos--;
            }
        }
        // 2a. b < a.size()
        while (p1 != null) {
            p1prev = p1;
            p2prev = p2;
            p1 = p1.next;
            p2 = p2.next;
        }

        // 3. shift
        p1prev.next = a;
        p2prev.next = null;
        head = p2;
        return head;
    }
}
