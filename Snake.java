import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    public static void main(String[] args) {
        char[][] data = new char[][]{
            {'a', 'e', 'e'},
            {'n', 's', 'n'},
            {'e', 'k', 'a'}
        };
        String test = "seenseen";
        for (char[] row : data)
            System.out.println(Arrays.toString(row));
        System.out.println();

        Snake s = new Snake();
        System.out.println(s.find(data, test));
    }

    class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() { return "[" + a + ", " + b + "]"; }
    }

    public List<Pair> find(char[][] data, String test) {
        if (test == null || test.length() < 1) return Collections.emptyList();

        int index = 0;
        char c = test.charAt(index);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == c) {
                    LinkedList<Pair> result = new LinkedList<Pair>();
                    dfs(data, i, j, test, index, result);
                    return result;
                }
            }
        }
        return Collections.emptyList();
    }

    private void dfs(char[][] data, int i, int j, String test, int index, LinkedList<Pair> result) {
        if (index >= test.length() || i < 0 || j < 0 || i >= data.length || j >= data[i].length ||
            data[i][j] != test.charAt(index)) {
            return;
        }
        if (data[i][j] == test.charAt(index)) {
            System.out.println("data[i,j] = " + data[i][j] + ", [" + i + ", " + j + "]");
            result.add(new Pair(i, j));
            if (result.size() == test.length())
                System.out.println(result);
        }

        dfs(data, i - 1, j, test, index + 1, result);
        dfs(data, i, j - 1, test, index + 1, result);
        dfs(data, i + 1, j, test, index + 1, result);
        dfs(data, i, j + 1, test, index + 1, result);

        if (!result.isEmpty()) result.removeLast();
    }
}

