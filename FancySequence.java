import java.util.ArrayList;
import java.util.Arrays;

/**
 * LeetCode
 * 1622. Fancy Sequence
 * https://leetcode.com/problems/fancy-sequence/
 * #Hard
 */
public class FancySequence {
    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        String[] commands = {"append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"};
        int[][] arguments = {{2}, {3}, {7}, {2}, {0}, {3}, {10}, {2}, {0}, {1}, {2}};
        Object[] expected = {null, null, null, null, 10, null, null, null, 26, 34, 20};
        Object[] res = check(fancy, commands, arguments);
        System.out.println(Arrays.toString(res)); // [null, null, null, null, 10, null, null, null, 26, 34, 20]
        System.out.println(Arrays.toString(expected)); // [null, null, null, null, 10, null, null, null, 26, 34, 20]
        System.out.println(Arrays.equals(expected, res));

        commands = new String[]{"append", "append", "getIndex", "append", "getIndex", "addAll", "append", "getIndex", "getIndex", "append", "append", "getIndex", "append", "getIndex", "append", "getIndex", "append", "getIndex", "multAll", "addAll", "getIndex", "append", "addAll", "getIndex", "multAll", "getIndex", "multAll", "addAll", "addAll", "append", "multAll", "append", "append", "append", "multAll", "getIndex", "multAll", "multAll", "multAll", "getIndex", "addAll", "append", "multAll", "addAll", "addAll", "multAll", "addAll", "addAll", "append", "append", "getIndex"};
        arguments = new int[][]{{12}, {8}, {1}, {12}, {0}, {12}, {8}, {2}, {2}, {4}, {13}, {4}, {12}, {6}, {11}, {1}, {10}, {2}, {3}, {1}, {6}, {14}, {5}, {6}, {12}, {3}, {12}, {15}, {6}, {7}, {8}, {13}, {15}, {15}, {10}, {9}, {12}, {12}, {9}, {9}, {9}, {9}, {4}, {8}, {11}, {15}, {9}, {1}, {4}, {10}, {9}};
        expected = new Object[]{null, null, 8, null, 12, null, null, 24, 24, null, null, 4, null, 12, null, 20, null, 24, null, null, 37, null, null, 42, null, 360, null, null, null, null, null, null, null, null, null, 220560, null, null, null, 285845760, null, null, null, null, null, null, null, null, null, null, 150746316};
        res = check(fancy, commands, arguments);
        System.out.println(Arrays.toString(res)); // [null,null,8,null,12,null,null,24,24,null,null,4,null,12,null,20,null,24,null,null,37,null,null,42,null,360,null,null,null,null,null,null,null,null,null,220560,null,null,null,285845760,null,null,null,null,null,null,null,null,null,null,150746316]
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.equals(expected, res));

    }

    private static Object[] check(Fancy fancy, String[] commands, int[][] arguments) {
        Object[] res = new Object[commands.length];
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            int[] arg = arguments[i];
            switch (command) {
                case "append":
                    fancy.append(arg[0]);
                    res[i] = null;
                    break;
                case "addAll":
                    fancy.addAll(arg[0]);
                    res[i] = null;
                    break;
                case "multAll":
                    fancy.multAll(arg[0]);
                    res[i] = null;
                    break;
                case "getIndex":
                    res[i] = fancy.getIndex(arg[0]);
                    break;
            }
        }
        return res;
    }

    static class Fancy {
        private static final int MOD = 1_000_000_007;
        private final ArrayList<int[]> valueOpSize = new ArrayList<>();
        private final ArrayList<Integer> operations = new ArrayList<>();

        public Fancy() {
        }

        public void append(int val) {
            valueOpSize.add(new int[]{val, operations.size()});
        }

        public void addAll(int inc) {
            operations.add(inc);
        }

        public void multAll(int m) {
            operations.add(-m);
        }

        public int getIndex(int idx) {
            if (idx >= valueOpSize.size()) {
                return -1;
            }

            int[] valueOp = valueOpSize.get(idx);
            int value = valueOp[0];
            int opSize = valueOp[1];

            for (int i = opSize; i < operations.size(); i++) {
                int op = operations.get(i);
                if (op < 0) {
                    value = (int) (((long) value * -op) % MOD);
                } else {
                    value = (value + op ) % MOD;
                }
            }

            valueOp[0] = value;
            valueOp[1] = operations.size();

            return value;
        }
    }
}
