/**
 * LeetCode
 * 1649. Create Sorted Array through Instructions
 * https://leetcode.com/problems/create-sorted-array-through-instructions/
 * #Hard #Tree #BITree #FenwickTree
 */
public class CreateSortedArrayThroughInstructions {
    private static final int MOD = 1_000_000_000 + 7;

    public static void main(String[] args) {
        CreateSortedArrayThroughInstructions sol = new CreateSortedArrayThroughInstructions();
        System.out.println(sol.createSortedArray(new int[]{})); // 0
        System.out.println(sol.createSortedArray(new int[]{1, 5, 6, 2})); // 1
        System.out.println(sol.createSortedArray(new int[]{1, 2, 3, 6, 5, 4})); // 3
        System.out.println(sol.createSortedArray(new int[]{1, 3, 3, 3, 2, 4, 2, 1, 2})); // 4
    }

    private static class BITree {
        private final int[] tree;

        public BITree(int n) {
            tree = new int[n + 1];
        }

        void update(int idx, @SuppressWarnings("SameParameterValue") int k) {
            while (idx < tree.length) {
                tree[idx] += k;
                idx += idx & -idx;
            }
        }

        int getSum(int idx) {
            int res = 0;
            while (idx > 0) {
                res += tree[idx];
                idx -= idx & -idx;
            }
            return res;
        }
    }

    public int createSortedArray(int[] instructions) {
        if (instructions == null || instructions.length == 0) return 0;

        int max = 0;
        for (int instruction : instructions) {
            max = Math.max(max, instruction);
        }
        BITree tree = new BITree(max);

        int count = 0;
        for (int i = 0; i < instructions.length; i++) {
            int instruction = instructions[i];
            int left = tree.getSum(instruction - 1);
            int right = i - tree.getSum(instruction);

            count += Math.min(left, right);
            count %= MOD;
            tree.update(instruction, 1);
        }
        return count;
    }
}
