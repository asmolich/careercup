import java.util.*;

public class LinkedListInsertionSort {
    static class ListNode {
        int value;
        ListNode next;
        ListNode(int v){value=v;}
		@Override
		public String toString(){ return ""+value;}
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(5);
        ListNode temp = list;
        temp.next = new ListNode(4); temp = temp.next;
        temp.next = new ListNode(7); temp = temp.next;
        temp.next = new ListNode(1); temp = temp.next;
        temp.next = new ListNode(0); temp = temp.next;
        temp.next = new ListNode(8); temp = temp.next;
        temp.next = new ListNode(3); temp = temp.next;
        temp.next = new ListNode(4); temp = temp.next;
        temp.next = new ListNode(5); temp = temp.next;
        temp.next = new ListNode(9); temp = temp.next;
        temp.next = new ListNode(6);

        // Expected: 0 1 3 4 4 5 5 6 7 8 9
		System.out.println("Input: ");
        print(list);
		System.out.println("Sorted: ");
		print(sort(list));
    }

    public static ListNode sort(final ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode sorted = head;
        ListNode unsorted = head.next;
        ListNode unsortedPrev = head;
        while(unsorted.next != null) {
		    //System.out.println("unsorted = " + unsorted);
            if (unsortedPrev.value > unsorted.value) {
                unsortedPrev.next = unsorted.next;
                sorted = insert(sorted, unsorted, unsorted.next);
            }
            unsortedPrev = unsorted;
            unsorted = unsorted.next;
        }
        return sorted;
    }

    private static void print(ListNode node) {
        if (node == null) return;

		while (node.next != null) {
			System.out.print("" + node + " ");
			node = node.next;
        }
        System.out.println();
    }

    private static ListNode insert(final ListNode head, final ListNode ins, final ListNode tail) {
        if (head == ins) {
            head.next = tail;
            return head;
        }
        if (head.value > ins.value) {
            ins.next = head;
            return ins;
        }
        ListNode node = head;
        ListNode prev = node;
        while (node.next != null && node.value <= ins.value) {
            prev = node;
            node = node.next;
        }
        ins.next = node;
        prev.next = ins;
        return head;
    }
}
