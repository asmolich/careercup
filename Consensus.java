import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Indeed
 * <p>
 * Given n iterators. Collect only those numbers that occur in more than k iterators.
 * <p>
 * #Medium #Iterator #Interview
 * #Incomplete
 */
public class Consensus {
    public static void main(String[] args) {

    }

    static class MyIterator implements Iterator<Integer> {

        public Integer peek() {
            return 0;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Integer next() {
            return null;
        }
    }

    List<Integer> findConsensus(List<MyIterator> data, int k) {

        if (data == null || data.isEmpty() || k < 1) return Collections.emptyList();

        List<Integer> result = new ArrayList<>();

        PriorityQueue<MyIterator> pq = new PriorityQueue<>(Comparator.comparingInt(MyIterator::peek));
        pq.addAll(data);

        while (!pq.isEmpty()) {
            MyIterator currMin = pq.poll();
            Set<MyIterator> same = new HashSet<>();
            for (MyIterator it : pq) {
                if (it.peek().equals(currMin.peek())) {
                    same.add(it);
                }
            }
            if (same.size() >= k) result.add(currMin.peek());

            while (!pq.isEmpty()) {
                final MyIterator it = pq.poll();
                final Integer current = it.peek();
                if (current.equals(currMin.peek())) {
                    while (it.hasNext() && it.peek().equals(current)) it.next();
                }


            }
        }

        return result;
    }
}
