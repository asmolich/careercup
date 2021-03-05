import java.util.concurrent.Semaphore;

/**
 * LeetCode
 * 1114. Print in Order
 * https://leetcode.com/problems/print-in-order/
 * #Easy
 */
@SuppressWarnings("unused")
public class ConcurrencyPrintInOrder {
    private final Semaphore[] semaphore = {
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)};

    public ConcurrencyPrintInOrder() {
        try {
            for (int i = 1; i < semaphore.length; i++) {
                semaphore[i].acquire();
            }
        } catch (Exception ignored) {
        }
    }

    public void first(Runnable printFirst) throws InterruptedException {
        semaphore[0].acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaphore[1].release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphore[1].acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        semaphore[2].release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphore[2].acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        //semaphore[0].release();
    }
}
