import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * LeetCode
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * #Hard #Heap
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        MergeKSortedLists sol = new MergeKSortedLists();
        ListNode[] lists = {
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6)),
        };
        System.out.println(sol.mergeKLists(lists)); // [1,1,2,3,4,4,5,6]
        System.out.println(sol.mergeKLists(new ListNode[]{})); // []
        System.out.println(sol.mergeKLists(new ListNode[]{null})); // []
        System.out.println(sol.mergeKLists(new ListNode[]{null, new ListNode(1)})); // [1]
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
            return val + (next == null ? "" : ", " + next);
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        pq.addAll(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));
        ListNode head = null;
        ListNode prev = null;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            if (node.next != null) pq.add(node.next);
            if (head == null) head = node;
            if (prev != null) prev.next = node;
            prev = node;
        }
        return head;
    }
}
