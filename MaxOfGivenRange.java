/**
 * Max of given range | Segment Tree
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * #Hard #SegmentTree
 */
public class MaxOfGivenRange {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree tree = new SegmentTree(arr);

        // Print sum of values in array from index 1 to 3
        System.out.println("Max of values in given range = " + tree.getMax(1, 3));

        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(1, 10);

        // Find max after the value is updated
        System.out.println("Updated sum of values in given range = " + tree.getMax(1, 3));
    }

    private static class SegmentTree {
        private final int[] tree;
        private final int size;

        public SegmentTree(int[] arr) {
            size = arr.length;
            tree = new int[size * 2];

            System.arraycopy(arr, 0, tree, size, size);

            for (int i = size - 1; i >= 0; i--) {
                tree[i] = Math.max(tree[2 * i], tree[2 * i + 1]);
            }
        }

        public void updateValue(int i, int value) {
            i += size;
            tree[i] = value;
            int newMax;
            while (i > 0) {
                i /= 2;
                newMax = Math.max(tree[2 * i], tree[2 * i + 1]);
                if (tree[i] != newMax) {
                    tree[i] = newMax;
                } else {
                    return;
                }
            }
        }

        public int getMax(int from, int to) {
            if (from < 0 || to >= size || from > to) {
                throw new IllegalArgumentException("Invalid Input");
            }
            from += size;
            to += size;
            int max = Integer.MIN_VALUE;
            while (from < to) {
                if ((from & 1) == 1) {
                    max = Math.max(max, tree[from]);
                    from++;
                }
                if ((to & 1) == 1) {
                    to--;
                    max = Math.max(max, tree[to]);
                }
                from /= 2;
                to /= 2;
            }
            return max;
        }
    }
}
