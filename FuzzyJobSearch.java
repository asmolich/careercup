import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * HackerRank. Indeed Onsite.
 * Fuzzy Job Search
 * https://www.hackerrank.com/tests/glfngtjgbil/questions/21gha8p0oak
 */
public class FuzzyJobSearch {
    public static void main(String[] args) {
        FuzzyJobSearch.storeDocument("pet washer cats cats cats", 0);
        FuzzyJobSearch.storeDocument("java dev", 1);
        FuzzyJobSearch.storeDocument("driver for cats", 2);


        System.out.println(FuzzyJobSearch.performSearch("driver cats"));
        System.out.println(FuzzyJobSearch.performSearch("cats"));
        System.out.println(FuzzyJobSearch.performSearch("dogs"));
        System.out.println(FuzzyJobSearch.performSearch("javadev"));
    }

    private static final Map<String, Set<Integer>> index = new HashMap<>();

    private static void storeDocument(final String document, final int documentNumber) {
        StringTokenizer tokenizer = new StringTokenizer(document, " ");
        while (tokenizer.hasMoreElements()) {
            final String word = tokenizer.nextToken();

            Set<Integer> set = index.getOrDefault(word, new HashSet<>()); //order in set?
            set.add(documentNumber);

            index.put(word, set);
        }
    }

    static class Pair {
        Integer idx, count;

        Pair(Integer idx, Integer count) {
            this.idx = idx;
            this.count = count;
        }
    }

    private static String performSearch(final String search) {
        StringTokenizer tokenizer = new StringTokenizer(search, " ");

        Map<Integer, Integer> hist = new HashMap<>();

        while (tokenizer.hasMoreElements()) {
            final String word = tokenizer.nextToken();

            if (index.containsKey(word)) {
                Set<Integer> set = index.get(word);
                for (Integer i : set) {
                    hist.put(i, hist.getOrDefault(i, 0) + 1);
                }
            }
        }

        final List<String> result = hist.keySet().stream()
                .map(k -> new Pair(k, hist.get(k)))
                .sorted((a, b) -> {
                    final int cmp = Integer.compare(a.count, b.count);
                    if (cmp == 0) return Integer.compare(a.idx, b.idx); // use order statistics instead
                    return -cmp;
                })
                .limit(10)
                .map(p -> p.idx)
                .map(Object::toString)
                .collect(Collectors.toList());

        if (result.size() > 0) return String.join(" ", result);
        return "-1";
    }
}
