import java.util.Timer;
import java.util.TimerTask;

/**
 * Daily Coding Problem: Problem #630
 * <p>
 * Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
 * <p>
 * #Medium #Apple
 */
public class JobScheduler {
    // timer has timer queue and timer thread
    private final Timer timer = new Timer();

    public void schedule(Runnable fn, long delay) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fn.run();
            }
        }, delay);
    }
}
