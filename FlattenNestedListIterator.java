import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * LeetCode
 * 341. Flatten Nested List Iterator
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * #Medium #Stack
 */
@SuppressWarnings("unused")
public class FlattenNestedListIterator {
    private static class NestedIterator implements Iterator<Integer> {
        private final Deque<NestedInteger> deq = new ArrayDeque<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            add(nestedList);
        }

        private void add(List<NestedInteger> nestedList) {
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) deq.add(ni);
                else add(ni.getList());
            }
        }

        @Override
        public Integer next() {
            if (deq.isEmpty()) throw new NoSuchElementException();
            return deq.poll().getInteger();
        }

        @Override
        public boolean hasNext() {
            return !deq.isEmpty();
        }
    }
}
