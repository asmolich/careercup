import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T> {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null, null, null, 11, 12);
        FilterIterator<Integer> fi = new FilterIterator<>(list.iterator(), el -> el == null || el % 2 == 0);
        System.out.print("Result: [ ");
        while (fi.hasNext()) {
            Integer n = fi.next();
            System.out.print("" + n + ", ");
        }
        System.out.println("]");
    }

    private final Iterator<T> it;
    private final Predicate<T> filter;

    private T next = null;

    enum State {
        // next is not calculated
        NOT_READY,
        // next is calculated, but not returned
        READY,
        // next is calculated and return
        DONE
    }

    private State state = State.NOT_READY;

    FilterIterator(Iterator<T> i, Predicate<T> p) {
        it = i;
        filter = p;
    }

    @Override
    public boolean hasNext() {
        if (state == State.DONE) return false;
        boolean result = false;
        if (state == State.READY) result = true;
        else {
            while (it.hasNext()) {
                T elem = it.next();
                if (filter.test(elem)) {
                    next = elem;
                    result = true;
                    state = State.READY;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T result = next;
            next = null;
            state = State.NOT_READY;
            return result;
        }
        state = State.DONE;
        return null;
    }
}

