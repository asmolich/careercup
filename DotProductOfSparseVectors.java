import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode
 * 1570. Dot Product of Two Sparse Vectors
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 * https://walkccc.me/LeetCode/problems/1570/
 * https://leetcode.com/discuss/interview-question/algorithms/124823/dot-product-of-sparse-vector
 */
public class DotProductOfSparseVectors {
    public static void main(String[] args) {
        SparseVectorHashMap sahm1 = new SparseVectorHashMap().append(1, 2).append(2, 3).append(100, 5);
        SparseVectorHashMap sahm2 = new SparseVectorHashMap().append(0, 5).append(1, 1).append(100, 6);
        System.out.println(sahm1.dotProduct(sahm2));
        SparseVectorArrayList saal1 = new SparseVectorArrayList().append(1, 2).append(2, 3).append(100, 5);
        SparseVectorArrayList saal2 = new SparseVectorArrayList().append(0, 5).append(1, 1).append(100, 6);
        System.out.println(saal1.dotProduct(saal2));
    }

    //Solution 1: Two pointers.
    //Solution 2: HashMap
    //Solution 3: If one is big, one is small, iterate over small, and do a binary search in the big one.
    private static class SparseVectorHashMap {
        private final Map<Integer, Integer> map = new HashMap<>();

        public SparseVectorHashMap append(int i, int value) {
            map.put(i, value);
            return this;
        }

        public int dotProduct(SparseVectorHashMap v) {
            SparseVectorHashMap a = this;
            SparseVectorHashMap b = v;
            if (map.size() > v.map.size()) {
                a = v;
                b = this;
            }

            int res = 0;
            for (var entry : a.map.entrySet()) {
                int idx = entry.getKey();
                if (b.map.containsKey(idx)) {
                    res += b.map.get(idx) * entry.getValue();
                }
            }
            return res;
        }
    }

    private static class SparseVectorArrayList {
        private static class KV {
            int k, v;
        }

        private final List<KV> kvs = new ArrayList<>();
        private KV last = null;

        public SparseVectorArrayList append(int i, int value) {
            if (last != null && last.k > i) throw new IllegalArgumentException("The sparse array must be sorted");
            KV kv = new KV();
            kv.k = i;
            kv.v = value;
            kvs.add(kv);
            last = kv;
            return this;
        }

        public int dotProduct(SparseVectorArrayList v) {
            SparseVectorArrayList a = this;
            SparseVectorArrayList b = v;
            if (kvs.size() > v.kvs.size()) {
                a = v;
                b = this;
            }

            int res = 0;
            for (int i = 0, j = 0; i < a.kvs.size() && j < b.kvs.size(); ) {
                KV kva = a.kvs.get(i);
                KV kvb = b.kvs.get(j);
                if (kva.k == kvb.k) {
                    res += kva.v * kvb.v;
                    i++;
                    j++;
                } else if (kva.k < kvb.k) {
                    i++;
                } else {
                    j++;
                }
            }
            return res;
        }
    }
}
