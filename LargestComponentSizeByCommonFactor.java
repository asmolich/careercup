import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 952. Largest Component Size by Common Factor
 * https://leetcode.com/problems/largest-component-size-by-common-factor/
 * #Hard
 */
public class LargestComponentSizeByCommonFactor {
    public static void main(String[] args) {
        LargestComponentSizeByCommonFactor sol = new LargestComponentSizeByCommonFactor();
        System.out.println(sol.largestComponentSize(new int[]{4, 6, 15, 35})); // 4
        System.out.println(sol.largestComponentSize(new int[]{20, 50, 9, 63})); // 2
        System.out.println(sol.largestComponentSize(new int[]{2, 3, 6, 7, 4, 12, 21, 39})); // 8
        System.out.println(sol.largestComponentSize(new int[]{98, 99, 7, 40, 41, 42, 47, 81, 51, 20, 24, 59, 31})); // 9
    }

    private static class UnionFind {
        private int count;
        private final int[] roots;
        private final int[] sizes;
        private int max = 1;

        public UnionFind(int n) {
            this.count = n;
            roots = new int[n];
            sizes = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
                sizes[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int a) {
            if (a == roots[a]) return a;
            return roots[a] = find(roots[a]);
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);

            if (ra == rb) return;

            if (sizes[ra] >= sizes[rb]) {
                roots[rb] = ra;
                sizes[ra] += sizes[rb];
                max = Math.max(max, sizes[ra]);
            } else {
                roots[ra] = rb;
                sizes[rb] += sizes[ra];
                max = Math.max(max, sizes[rb]);
            }
            count--;
        }

        public int getLargestCC() {
            return max;
        }

        @Override
        public String toString() {
            return "uf{size=" + count + ", roots=" + Arrays.toString(roots) + '}';
        }
    }

    public int largestComponentSize(int[] a) {
        if (a == null || a.length == 0) return 0;

        int n = a.length;
        UnionFind uf = new UnionFind(n);
        Map<Integer, Integer> factorGroups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int factor = 2; factor * factor <= a[i]; factor++) {
                if (a[i] % factor == 0) {
                    if (!factorGroups.containsKey(factor)) factorGroups.put(factor, i);
                    else uf.union(factorGroups.get(factor), i);
                    int factor2 = a[i] / factor;
                    if (!factorGroups.containsKey(factor2)) factorGroups.put(factor2, i);
                    else uf.union(factorGroups.get(factor2), i);
                }
            }
            if (a[i] > 1) {
                int factor3 = a[i];
                if (!factorGroups.containsKey(factor3)) factorGroups.put(factor3, i);
                else uf.union(factorGroups.get(factor3), i);
            }
        }
        return uf.getLargestCC();
    }
}
