import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NestedInteger {
    List<Integer> list;
    int val;

    public NestedInteger(List<Integer> list) {
        this.list = list;
        val = -1;
    }

    public NestedInteger(int[] arr) {
        list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        val = -1;
    }

    public NestedInteger(int arg, int... args) {
        if (args == null || args.length == 0) {
            list = null;
            val = arg;
        } else {
            list = IntStream.concat(IntStream.of(arg), Arrays.stream(args)).boxed().collect(Collectors.toList());
            val = -1;
        }
    }

    /**
     * @return true if this NestedInteger holds a single integer, rather than a nested list.
     */
    public boolean isInteger() {
        return list == null;
    }

    /**
     * @return the single integer that this NestedInteger holds, if it holds a single integer
     * Return null if this NestedInteger holds a nested list
     */
    public Integer getInteger() {
        return val;
    }

    /**
     * @return the nested list that this NestedInteger holds, if it holds a nested list
     * Return null if this NestedInteger holds a single integer
     */
    public List<NestedInteger> getList() {
        if (list == null) return null;
        return list.stream().map(NestedInteger::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "" + (list == null ? val : list);
    }
}