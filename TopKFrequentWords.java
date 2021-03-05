import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode
 * 692. Top K Frequent Words
 * https://leetcode.com/problems/top-k-frequent-words/
 * #Medium #TopK #HashTable
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        TopKFrequentWords sol = new TopKFrequentWords();
        System.out.println(sol.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2)); // ["i", "love"]
        System.out.println(sol.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)); // ["the", "is", "sunny", "day"]
        System.out.println(sol.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 14)); // ["the", "is", "sunny", "day"]
    }

    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) return Collections.emptyList();
        Map<String, Integer> counts = new HashMap<>();
        int max = 0;
        for (String word : words) {
            int value = counts.getOrDefault(word, 0) + 1;
            max = Math.max(max, value);
            counts.put(word, value);
        }
        //noinspection unchecked
        Queue<String>[] buckets = new Queue[max + 1];
        for (var entry : counts.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue();
            if (buckets[val] == null) buckets[val] = new PriorityQueue<>();
            buckets[val].add(key);
        }

        List<String> res = new ArrayList<>();
        for (int i = max; i >= 0 && k > 0; i--) {
            if (buckets[i] == null) continue;
            while (k > 0 && !buckets[i].isEmpty()) {
                res.add(buckets[i].poll());
                k--;
            }
        }
        return res;
    }
}
