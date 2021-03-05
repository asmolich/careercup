import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://rosettacode.org/wiki/Sorting_algorithms/Patience_sort#Java
 * https://www.youtube.com/watch?v=22s1xxRvy28
 * <p>
 * LIS in O(N*logN)
 */
public class PatienceSort {
    public static void main(String[] args) {
        Integer[] a = {4, 65, 2, -31, 0, 99, 83, 782, 1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static <E extends Comparable<? super E>> void sort(E[] arr) {
        List<Deque<E>> piles = new ArrayList<>();
        Comparator<? super Deque<E>> c = Comparator.comparing(Deque::peekLast);
        // sort into piles
        for (E a : arr) {
            Deque<E> newPile = new ArrayDeque<>();
            newPile.addLast(a);
            int i = Collections.binarySearch(piles, newPile, c);
            if (i < 0) i = ~i;
            if (i != piles.size())
                piles.get(i).addLast(a);
            else
                piles.add(newPile);
        }

        // priority queue allows us to retrieve least pile efficiently
        PriorityQueue<Deque<E>> minQ = new PriorityQueue<>(c);
        minQ.addAll(piles);
        for (int i = 0; i < arr.length; i++) {
            Deque<E> smallPile = minQ.poll();
            if (smallPile == null) continue;
            arr[i] = smallPile.removeLast();
            if (!smallPile.isEmpty())
                minQ.add(smallPile);
        }
        assert minQ.isEmpty();
    }
}