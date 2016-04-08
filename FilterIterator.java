import java.util.*;
import java.util.function.*;
public class FilterIterator<T> implements Iterator<T> {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        FilterIterator<Integer> fi = new FilterIterator<Integer>(list.iterator(), el -> el % 2 == 0);
        System.out.print("Result: [ ");
        while(fi.hasNext()) {
            Integer n = fi.next();
            System.out.print("" + n + ", ");
        }
        System.out.println("]");
    }

    private final Iterator<T> it;
    private final Predicate<T> filter;

    private T next = null;

    FilterIterator(Iterator<T> i, Predicate<T> p) {
        it = i;
        filter = p;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (next == null) {
            while(it.hasNext()) {
                T elem = it.next();
                if (filter.test(elem)) {
                    next = elem;
                    result = true;
                    break;
                }
            }
        }
        else {
            result = true;
        }
        return result;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T result = next;
            next = null;
            return result;
        }
        return null;
    }
}

