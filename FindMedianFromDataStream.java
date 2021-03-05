import java.util.PriorityQueue;

/**
 * LeetCode
 * 295. Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 * #Hard #Heap
 */
public class FindMedianFromDataStream {
    private static class MedianFinder {
        private final PriorityQueue<Integer> minQ = new PriorityQueue<>();
        private final PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        public MedianFinder() {
        }

        public void addNum(int num) {
//            System.out.println("add " + num);
            int bottom = maxQ.size() > 0 ? maxQ.peek() : 0;
            int top = minQ.size() > 0 ? minQ.peek() : 0;

            if (num < bottom) {
                if (maxQ.size() > minQ.size()) {
                    minQ.add(maxQ.poll());
                }
                maxQ.add(num);
            } else if (num > top) {
                if (minQ.size() > maxQ.size()) {
                    maxQ.add(minQ.poll());
                }
                minQ.add(num);
            } else {
                if (minQ.size() > maxQ.size()) {
                    maxQ.add(num);
                } else {
                    minQ.add(num);
                }
            }
        }

        public double findMedian() {
//            System.out.println("minQ.size()=" + minQ.size() + ", maxQ.size()=" + maxQ.size());
//            System.out.println("minQ: " + minQ);
//            System.out.println("maxQ: " + maxQ);
            if (minQ.isEmpty()) return maxQ.isEmpty() ? 0 : (double) maxQ.peek();
            if (maxQ.isEmpty()) return (double) minQ.peek();

            if (minQ.size() == maxQ.size()) {
                return (minQ.peek() + maxQ.peek()) / 2.0d;
            } else {
                return minQ.size() > maxQ.size() ? minQ.peek() : maxQ.peek();
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder sol = new MedianFinder();
        sol.addNum(12);
        System.out.println(sol.findMedian() + ", expected = 12.0"); // 12.0
        sol.addNum(10);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(13);
        System.out.println(sol.findMedian() + ", expected = 12.0"); // 12.0
        sol.addNum(11);
        System.out.println(sol.findMedian() + ", expected = 11.5"); // 11.5
        sol.addNum(5);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(15);
        System.out.println(sol.findMedian() + ", expected = 11.5"); // 11.5
        sol.addNum(1);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(11);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(6);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(17);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(14);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(8);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(17);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(6);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(4);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(16);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(8);
        System.out.println(sol.findMedian() + ", expected = 11.0"); // 11.0
        sol.addNum(10);
        System.out.println(sol.findMedian() + ", expected = 10.5"); // 10.5
        sol.addNum(2);
        System.out.println(sol.findMedian() + ", expected = 10.0"); // 10.0
        sol.addNum(12);
        System.out.println(sol.findMedian() + ", expected = 10.5"); // 10.5
        sol.addNum(0);
        System.out.println(sol.findMedian() + ", expected = 10.0"); // 10.0
    }
}
