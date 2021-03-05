import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListToBST {
    private static class ListNode {
        int data;
        ListNode next;
        ListNode prev;

        ListNode(int d) {
            data = d;
        }
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int d) {
            data = d;
        }
    }

    private static TreeNode newNode(int data) {
        return new TreeNode(data);
    }

    static int countListNodes(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    /* This function counts the number of nodes in Linked List and then calls
       sortedListToBSTRecur() to construct BST */
    private static TreeNode sortedListToBST(LinkedList<Integer> head) {
        /*Count the number of nodes in Linked List */
        int n = head.size();//countListNodes(head);

        Iterator<Integer> it = head.iterator();
        /* Construct BST */
        if (it.hasNext()) {
            return sortedListToBSTRecur(it.next(), it, n);
        }
        return null;
    }

    /* The main function that constructs balanced BST and returns root of it.
       head_ref -->  Pointer to pointer to head node of linked list
       n  --> No. of nodes in Linked List */
    private static TreeNode sortedListToBSTRecur(Integer i, Iterator<Integer> it, int n) {
        /* Base Case */
        if (n <= 0 || !it.hasNext())
            return null;

        /* Recursively construct the left subtree */
        TreeNode left = sortedListToBSTRecur(i, it, n / 2);

        /* Allocate memory for root, and link the above constructed left
           subtree with root */
        TreeNode root = newNode(i);
        root.left = left;

        /* Change head pointer of Linked List for parent recursive calls */
        i = it.next();

        /* Recursively construct the right subtree and link it with root
          The number of nodes in right subtree  is total nodes - nodes in
          left subtree - 1 (for root) which is n-n/2-1*/
        root.right = sortedListToBSTRecur(i, it, n - n / 2 - 1);

        return root;
    }

    /* Function to insert a node at the beginging of the linked list */
    static ListNode push(ListNode head_ref, int new_data) {
        /* allocate node */
        ListNode new_node = new ListNode(new_data);

        /* link the old list off the new node */
        new_node.next = head_ref;

        /* move the head to point to the new node */
        return new_node;
    }

    /* Function to print nodes in a given linked list */
    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    /* A utility function to print preorder traversal of BST */
    static void preOrder(TreeNode node) {
        if (node == null)
            return;
        System.out.println(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {

        /* Start with the empty list */
        //ListNode head = null;

        /* Let us create a sorted linked list to test the functions
         Created linked list will be 1->2->3->4->5->6->7 */
        //head = push(head, 7);
        //head = push(head, 6);
        //head = push(head, 5);
        //head = push(head, 4);
        //head = push(head, 3);
        //head = push(head, 2);
        //head = push(head, 1);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        System.out.println("Given Linked List: " + list);
        //printList(head);
        //System.out.println();

        /* Convert List to BST */
        TreeNode root = sortedListToBST(list);
        System.out.println("PreOrder Traversal of constructed BST");
        preOrder(root);
        System.out.println();
    }
}

