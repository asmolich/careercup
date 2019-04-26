import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, -1, -3, 4, -7, 22, 50, 21);
        System.out.println(threeSumClosest(data, 30));
    }

    private static List<Integer> threeSumClosest(final List<Integer> a, int b) {
        Collections.sort(a);

        System.out.println(a);
        Integer[] arr = new Integer[a.size()];
        arr = a.toArray(arr);
        int gdiff = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int n1 = arr[i];
            int lo = i + 1;
            int hi = arr.length - 1;
            while (lo < hi) {
                int n2 = arr[lo];
                int n3 = arr[hi];
                int sum = n1 + n2 + n3;
                int diff = Math.abs(sum - b);
                if (diff == 0) return comp(n1, n2, n3);
                else if (diff < gdiff) {
                    gdiff = diff;
                    result = comp(n1, n2, n3);
                }
                if (sum > b) {
                    hi--;
                } else {
                    lo++;
                }
            }
        }
        return result;
    }

    private static List<Integer> comp(int n1, int n2, int n3) {
        List<Integer> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        return list;
    }
}

