import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
//        System.out.println(subsets(Arrays.asList(1, 2, 3, 4)));
        System.out.println(subsets(Arrays.asList(15, 20, 12, 19, 4)));
        System.out.println(subsets(Arrays.asList(1, 2, 2)));
    }

    private static List<List<Integer>> subsets(List<Integer> set) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> subset = new ArrayList<>();
        result.add(subset);

        if (set == null || set.isEmpty()) return result;

        set.sort(Comparator.comparingInt(a -> a));

        for (Integer i : set) {
            new ArrayList<>(result).forEach(r -> {
                ArrayList<Integer> newSubset = new ArrayList<>(r);
                newSubset.add(i);
                result.add(newSubset);
            });
        }

        result.sort((a, b) -> {
            int n = Math.min(a.size(), b.size());
            for (int i = 0; i < n; i++) {
                final int compare = Integer.compare(a.get(i), b.get(i));
                if (compare != 0) return compare;
            }
            return Integer.compare(a.size(), b.size());
        });

        return result;
    }
}
