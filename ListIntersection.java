import java.util.*;
public class ListIntersection {
	public static void main(String[] args) {
		Solution s = new Solution();
	    ListNode intersection = new ListNode(4, null);
	    ListNode a = new ListNode(1, new ListNode(2, intersection));
	    ListNode b = new ListNode(3, intersection);
        System.out.println("List a:");
        s.print(a);
        System.out.println("List b:");
        s.print(b);
        System.out.println("result:");
		System.out.println(s.getIntersectionNode(a, b).val);
	}
}

//Definition for singly-linked list.
class ListNode {
     public int val;
     public ListNode next;
     ListNode(int x) { val = x; next = null; }
     ListNode(int x, ListNode n) { val = x; next = n; }
}
 
class Solution {
	public void print(ListNode n) {
        if (n == null) return;

        while (n.next != null) {
			System.out.print("" + n.val + " ");
            n = n.next;
		}
        if (n.next == null)
            System.out.print("" + n.val + " ");
        
        System.out.println();
	}
 
	public ListNode getIntersectionNode(ListNode a, ListNode b) {
        if (a == null || b == null) return null;

        ListNode p1 = a;
        ListNode p2 = b;
        
        int sizeA = 0;
        int sizeB = 0;
        while (p1 != null) {
            p1 = p1.next;
            sizeA++;
        }
        while (p2 != null) {
            p2 = p2.next;
            sizeB++;
        }

        int d = 0;
        if (sizeA >= sizeB) {
            d = sizeA - sizeB;
            p1 = a;
            p2 = b;
        }
        else {
            d = sizeB - sizeA;
            p1 = b;
            p2 = a;
        }

        while(d > 0 && p1 != null) {
            p1 = p1.next;
            d--;
        }

        while(p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return null;
    }
}

