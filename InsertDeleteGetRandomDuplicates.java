import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Random;

/**
 * LeetCode
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * #Hard #HashTable
 */
public class InsertDeleteGetRandomDuplicates {
    private static class RandomizedCollection {
        private final ArrayList<Integer> values = new ArrayList<>();
        private final HashMap<Integer, Deque<Integer>> indexes = new HashMap<>();
        private int size = 0;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            Deque<Integer> places = indexes.get(val);
            if (places == null) {
                places = new ArrayDeque<>();
                indexes.put(val, places);
            }
            boolean result = places.isEmpty();
            places.add(size);
            if (size < values.size()) {
                values.set(size, val);
            } else {
                values.add(val);
            }
            size++;
            // System.out.println("insert "+val+". indexes = " + indexes);
            // System.out.println("insert "+val+". values = " + values);
            return result;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            Deque<Integer> idxs = indexes.get(val);
            if (idxs == null || idxs.isEmpty()) return false;
            Integer idx = idxs.poll();
            if (idx < size - 1) {
                Integer lastValue = values.get(size - 1);
                values.set(idx, lastValue);
                Deque<Integer> lastDeq = indexes.get(lastValue);
                int sz = lastDeq.size();
                for (int i = 0; i < sz; i++) {
                    Integer v = lastDeq.poll();
                    if (v != null && v == size - 1) {
                        lastDeq.add(idx);
                    } else {
                        lastDeq.add(v);
                    }
                }
            }
            size--;
            // System.out.println("remove "+val+". indexes = " + indexes);
            // System.out.println("remove "+val+". values = " + values);
            // System.out.println("size = " + size);
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            int idx = new Random().nextInt(size);
            // System.out.println("getRandom. indexes = " + indexes);
            // System.out.println("getRandom. values = " + values);
            // System.out.println("size = " + size);
            // System.out.println("random = " + idx);
            return values.get(idx);
        }
    }

    public static void main(String[] s) {
        String[] commands = {"insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"};
        int[][] args = {{1}, {2}, {2}, {}, {1}, {2}, {}};
        Object[] expected = {true, false, true, 2, true, false, 2};
        check(commands, args, expected);
    }

    private static void check(String[] commands, int[][] args, Object[] expected) {
        RandomizedCollection obj = new RandomizedCollection();
        int n = commands.length;
        for (int i = 0; i < n; i++) {
            String command = commands[i];
            switch (command) {
                case "insert":
                    if (obj.insert(args[i][0]) != (boolean) expected[i]) System.out.println("Case #" + i + " FAILED");
                    break;
                case "remove":
                    if (obj.remove(args[i][0]) != (boolean) expected[i]) System.out.println("Case #" + i + " FAILED");
                    break;
                case "getRandom":
                    int rand = obj.getRandom();
                    if (rand != (int) expected[i]) System.out.println("Case #" + i + " random = " + rand);
                    break;
            }
        }
    }
}
