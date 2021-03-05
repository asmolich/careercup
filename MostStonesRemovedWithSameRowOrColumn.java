import java.util.Arrays;

/**
 * LeetCode
 * 947. Most Stones Removed with Same Row or Column
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 * #Medium
 * #incomplete
 */
public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        MostStonesRemovedWithSameRowOrColumn sol = new MostStonesRemovedWithSameRowOrColumn();
        System.out.println(sol.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}})); // 5
        System.out.println(sol.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}})); // 3
        System.out.println(sol.removeStones(new int[][]{{0, 0}})); // 0
    }

    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        // 1 1 0
        // 1 0 1
        // 0 1 1

        int max = 0;
        for (int[] stone : stones) {
            max = Math.max(max, stone[0]);
            max = Math.max(max, stone[1]);
        }
        max++;

        UnionFind uf = new UnionFind(2 * max);
//        uf.union(0, max);
        for (int[] stone : stones) {
            System.out.println("stone =" + Arrays.toString(stone));
            uf.union(stone[0], max + stone[1]);
            uf.union(stone[1], max + stone[0]);
        }
        System.out.println("uf roots =" + Arrays.toString(uf.roots));
        System.out.println("uf sizes =" + Arrays.toString(uf.sizes));
        System.out.println("max cc = " + uf.getMaxCC());
        return uf.getMaxCC() - 1;
    }

    private static class UnionFind {
        private final int[] roots;
        private final int[] sizes;
        private int size;

        UnionFind(int n) {
            roots = new int[n];
            sizes = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
                sizes[i] = 1;
            }
            size = n;
        }

        int find(int a) {
            if (roots[a] != a) roots[a] = find(roots[a]);
            return roots[a];
        }

        void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return;
            if (sizes[ra] > sizes[rb]) {
                roots[rb] = ra;
                sizes[ra] += sizes[rb];
            } else {
                roots[ra] = rb;
                sizes[rb] += sizes[ra];
            }
            size--;
        }

        int getMaxCC() {
            int max = 0;
            for (int i = 0; i < size; i++) {
                max = Math.max(max, sizes[find(i)]);
            }
            return max;
        }
    }
}
