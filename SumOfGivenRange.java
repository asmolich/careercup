/**
 * Sum of given range | Segment Tree
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * #Hard #SegmentTree
 */
public class SumOfGivenRange {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree tree = new SegmentTree(arr);

        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " + tree.getSum(1, 3));

        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " + tree.getSum(1, 3));
    }

    private static class SegmentTree {
        private final int[] tree;
        private final int size;

        public SegmentTree(int[] arr) {
            size = arr.length;
            tree = new int[size * 2];

            // If there is one element in array, store it in current node of
            // segment tree and return
//            if (ss == se) {
//                tree[si] = arr[ss];
//                return arr[ss];
//            }
//
//            // If there are more than one elements, then recur for left and
//            // right subtrees and store the sum of values in this node
//            int mid = getMid(ss, se);
//            st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
//                    constructSTUtil(arr, mid + 1, se, si * 2 + 2);
//            return st[si];
        }

        public void updateValue(int begin, int end) {

        }

        public int getSum(int begin, int end) {
            if (begin < 0 || end >= size || begin > end) {
                throw new IllegalArgumentException("Invalid Input");
            }

            return 0;
        }
    }
}
