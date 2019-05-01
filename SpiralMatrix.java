import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        List<List<Integer>> a = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4),
            Arrays.asList(5, 6),
            Arrays.asList(7, 8)
        );
        System.out.println(a);
        SpiralMatrix sm = new SpiralMatrix();
        System.out.println(sm.spiralOrder(a));
    }

    // DO NOT MODIFY THE LIST
    public List<Integer> spiralOrder(final List<List<Integer>> a) {
        if (a == null) return null;
        int n = a.size();
        int m = -1;
        for (int i = 0; i < n; i++) {
            if (a.get(i) == null) throw new IllegalArgumentException("input should be a matrix");
            else if (m < 0) m = a.get(i).size();

            if (a.get(i).size() != m) throw new IllegalArgumentException("input should be a matrix");
        }

        int min = Math.min(m, n);
        int layersNum = min / 2;
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> current;
        int layer = 0;
        for (; layer < layersNum; layer++) {
            // top
            current = a.get(layer);
            for (int i = layer; i < m - layer; i++) {
                result.add(current.get(i));
            }
            // right
            for (int i = layer + 1; i < n - 1 - layer; i++) {
                current = a.get(i);
                result.add(current.get(m - 1 - layer));
            }
            // bottom
            current = a.get(n - 1 - layer);
            for (int i = m - 1 - layer; i >= layer; i--) {
                result.add(current.get(i));
            }
            // left
            for (int i = n - 2 - layer; i > layer; i--) {
                current = a.get(i);
                result.add(current.get(layer));
            }
        }

        if (min % 2 != 0) {
            if (n > m) {
                for (int i = layersNum; i < n - layersNum; i++) {
                    result.add(a.get(i).get(layersNum));
                }
            } else {
                for (int i = layersNum; i < m - layersNum; i++) {
                    result.add(a.get(layersNum).get(i));
                }
            }
        }

        return result;
    }
}

