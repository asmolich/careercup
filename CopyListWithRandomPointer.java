/**
 * LeetCode
 * 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * #Medium
 */
public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        CopyListWithRandomPointer sol = new CopyListWithRandomPointer();
        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.random = head;
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.random = head.next.next;
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.random = head;
        head.next.next.random = head.next.next.next.next;

        System.out.println(sol.copyRandomList(head));
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        System.out.println("original");
        System.out.println(head);

        Node node = head;
        // duplicate nodes
        while (node != null) {
            Node prev = node;
            node = node.next;

            // copy of prev
            Node newNode = new Node(prev.val);
            newNode.random = prev.random;
            newNode.next = node;
            prev.next = newNode;
        }
        System.out.println("duplicated");
        System.out.println(head);

        node = head.next;
        // fix random pointers
        while (node != null) {
            if (node.random != null) node.random = node.random.next;
            node = node.next == null ? null : node.next.next;
        }
        System.out.println("fixed");
        System.out.println(head);

        node = head;
        Node node2 = node.next;
        Node head2 = node2;
        // split
        while (node != null && node2 != null) {
            if (node.next != null) node.next = node.next.next;
            if (node2.next != null) node2.next = node2.next.next;

            node = node.next;
            node2 = node2.next;
        }
        System.out.println("list one");
        System.out.println(head);
        System.out.println("list two");
        System.out.println(head2);
        return head2;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "(" + val + ", " + this.hashCode() + ", r=" + (random == null ? "null" : random.val + ", " + random.hashCode()) + ") -> " + next;
        }
    }
}
