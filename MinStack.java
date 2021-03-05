import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 155. Min Stack
 * https://leetcode.com/problems/min-stack/
 * #Easy
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // -3
        minStack.pop();
        System.out.println(minStack.top()); // 0
        System.out.println(minStack.getMin()); // -2
    }

    private final Deque<int[]> deq = new ArrayDeque<>();
    private int min = Integer.MAX_VALUE;

    public void push(int x) {
        min = Math.min(min, x);
        deq.add(new int[]{x, min});
    }

    public void pop() {
        deq.removeLast();
        if (deq.isEmpty())
            min = Integer.MAX_VALUE;
        else
            min = deq.peekLast()[1];
    }

    public int top() {
        int[] el = deq.peekLast();
        return el[0];
    }

    public int getMin() {
        return min;
    }
}
