import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * LeetCode
 * 380. Insert Delete GetRandom O(1)
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * #Medium #HashTable
 */
public class InsertDeleteGetRandom {
    private static class RandomizedSet {
        private final ArrayList<Integer> values = new ArrayList<>();
        private final HashMap<Integer, Integer> indexes = new HashMap<>();
        private int size = 0;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (indexes.containsKey(val)) return false;
            indexes.put(val, size);
            if (size < values.size()) {
                values.set(size, val);
            } else {
                values.add(val);
            }
            size++;
            //System.out.println("insert "+val+". indexes = " + indexes);
            //System.out.println("insert "+val+". values = " + values);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            Integer idx = indexes.remove(val);
            if (idx == null) return false;
            if (idx < size - 1) {
                Integer lastValue = values.get(size - 1);
                values.set(idx, lastValue);
                indexes.put(lastValue, idx);
            }
            size--;
            //System.out.println("remove "+val+". indexes = " + indexes);
            //System.out.println("remove "+val+". values = " + values);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            //System.out.println("getRandom. indexes = " + indexes);
            //System.out.println("getRandom. values = " + values);
            //System.out.println(size);
            int idx = new Random().nextInt(size);
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
        RandomizedSet obj = new RandomizedSet();
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
