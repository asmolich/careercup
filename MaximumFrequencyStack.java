import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode
 * 895. Maximum Frequency Stack
 * https://leetcode.com/problems/maximum-frequency-stack/
 * #Hard #HashTable #Stack
 */
public class MaximumFrequencyStack {
    public static void main(String[] args) {
        FreqStack stack = new FreqStack();
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // 1
        stack.push(2);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // 2
    }

    private static class FreqStack {
        private final Map<Integer, Integer> freq = new HashMap<>();
        private final List<Deque<Integer>> buckets = new ArrayList<>();

        public FreqStack() {
            buckets.add(new ArrayDeque<>()); // Zero frequency
        }

        public void push(int x) {
            int oldFreq = freq.getOrDefault(x, 0);
            int newFreq = oldFreq + 1;
            freq.put(x, newFreq);

            Deque<Integer> deq;
            if (newFreq > 0 && newFreq >= buckets.size()) {
                deq = new ArrayDeque<>();
                buckets.add(deq);
            } else {
                deq = buckets.get(newFreq);
            }
            deq.add(x);
        }

        public int pop() {
            int n = buckets.size();
            Deque<Integer> mostFrequent = buckets.get(n - 1);
            //noinspection ConstantConditions
            int res = mostFrequent.pollLast();
            if (mostFrequent.isEmpty()) buckets.remove(n - 1);
            freq.put(res, freq.getOrDefault(res, 0) - 1);
            return res;
        }
    }
}
