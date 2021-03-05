import java.util.Queue;

/**
 * Daily Coding Problem.
 *
 * Implement a queue using a set of fixed-length arrays.
 * The queue should support `enqueue`, `dequeue` and `size` operations.
 *
 * #Hard #Netflix
 */
public class QueueUsingFixedLengthArrays<T> {
    private int size = 0;

    QueueUsingFixedLengthArrays() {

    }

    public void enqueue(T obj) {
        size++;
    }

    public T dequeue() {
        size--;
        return null;
    }

    public int size() {
        return size;
    }
}
