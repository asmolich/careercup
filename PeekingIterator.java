import java.util.Iterator;

/**
 * LeetCode
 * 284. Peeking Iterator
 * https://leetcode.com/problems/peeking-iterator/
 * https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125
 * #Medium
 */
@SuppressWarnings("unused")
public class PeekingIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private Integer curr = null;

    public PeekingIterator(Iterator<Integer> it) {
        iterator = it;
        if (iterator.hasNext()) curr = iterator.next();
    }

    public Integer peek() {
        return curr;
    }

    @Override
    public Integer next() {
        Integer next = curr;
        if (iterator.hasNext()) curr = iterator.next();
        else curr = null;
        return next;
    }

    @Override
    public boolean hasNext() {
        return curr != null;
    }
}
