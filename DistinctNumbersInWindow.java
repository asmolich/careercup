import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistinctNumbersInWindow {
    private int[] distinctNums(int[] a, int b) {
        if (a == null || b < 1) return new int[0];

        Map<Integer, Integer> map = new HashMap<>(b);
        int i = 0;
        int bound = Math.min(b, a.length);
        for (; i < bound; i++) {
            int ai = a[i];
            Integer count = map.get(ai);
            if (count == null) count = 0;
            map.put(ai, count + 1);
        }

        int[] res = new int[a.length - b + 1];
        res[0] = map.keySet().size();
        for (int j = 1; i < a.length; i++) {
            int aj1 = a[j - 1];
            int ai = a[i];
            Integer count = map.get(aj1);
            if (count == null) count = 0;
            if (count <= 1)
                map.remove(aj1);
            else
                map.put(aj1, count - 1);

            count = map.get(ai);
            if (count == null) count = 0;
            map.put(ai, count + 1);

            res[j] = map.keySet().size();

            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        DistinctNumbersInWindow dist = new DistinctNumbersInWindow();
        System.out.println(Arrays.toString(dist.distinctNums(new int[]{1, 2, 1, 3, 4, 3}, 3)));
    }
}
