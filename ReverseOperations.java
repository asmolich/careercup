/**
 * Facebook Interview Preparation
 * <p>
 * Reverse Operations
 * <p>
 * You are given a singly-linked list that contains N integers. A subpart of the list is a contiguous set of even elements, bordered either by either end of the list or an odd element. For example, if the list is [1, 2, 8, 9, 12, 16], the subparts of the list are [2, 8] and [12, 16].
 * <p>
 * Then, for each subpart, the order of the elements is reversed. In the example, this would result in the new list, [1, 8, 2, 9, 16, 12].
 * <p>
 * The goal of this question is: given a resulting list, determine the original order of the elements.
 * <p>
 * Implementation detail:
 * You must use the following definition for elements in the linked list:
 * <pre>
 * class Node {
 *     int data;
 *     Node next;
 * }
 * </pre>
 * <p>
 * Signature
 * Node reverse(Node head)
 * <p>
 * Constraints
 * 1 <= N <= 1000, where N is the size of the list
 * 1 <= Li <= 10^9, where Li is the ith element of the list
 * <p>
 * Example
 * Input:
 * N = 6
 * list = [1, 2, 8, 9, 12, 16]
 * <p>
 * Output:
 * [1, 8, 2, 9, 16, 12]
 */
public class ReverseOperations {
    private static class Node {
        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }

    private Node createLinkedList(int[] arr) {
        Node head = null;
        Node tempHead = null;
        for (int v : arr) {
            if (head == null) {
                head = new Node(v);
                tempHead = head;
            } else {
                head.next = new Node(v);
                head = head.next;
            }
        }
        return tempHead;
    }

    private void printLinkedList(Node head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
            if (head != null)
                System.out.print(" ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        ReverseOperations sol = new ReverseOperations();
        int[] arr_1 = {1, 2, 8, 9, 12, 16};
        int[] expected1 = {1, 8, 2, 9, 16, 12};
        Node head_1 = sol.createLinkedList(arr_1);
        Node expected_1 = sol.createLinkedList(expected1);
        Node output_1 = sol.reverse(head_1);
        System.out.println("Expected:");
        sol.printLinkedList(expected_1);
        System.out.println();
        System.out.println("Actual:");
        sol.printLinkedList(output_1);
        System.out.println();

        int[] arr_2 = {2, 18, 24, 3, 5, 7, 9, 6, 12};
        int[] expected2 = {24, 18, 2, 3, 5, 7, 9, 12, 6};
        Node head_2 = sol.createLinkedList(arr_2);
        Node expected_2 = sol.createLinkedList(expected2);
        Node output_2 = sol.reverse(head_2);
        System.out.println("Expected:");
        sol.printLinkedList(expected_2);
        System.out.println();
        System.out.println("Actual:");
        sol.printLinkedList(output_2);
        System.out.println();
    }

    public Node reverse(Node head) {
        if (head == null) return null;
        Node next = head.next;
        if ((head.data & 1) == 1) {
            head.next = reverse(next);
            return head;
        } else {
            if (next == null || (next.data & 1) == 1) {
                head.next = reverse(next);
                return head;
            }
            Node prev = head;
            while (next != null && (next.data & 1) == 0) {
                Node newNext = next.next;
                next.next = prev;
                prev = next;
                next = newNext;
            }
            Node newHead = prev;
            Node afterReverse = next;
            head.next = reverse(afterReverse);
            return newHead;
        }
    }
}
