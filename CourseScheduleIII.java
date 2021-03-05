import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode
 * 630. Course Schedule III
 * https://leetcode.com/problems/course-schedule-iii/
 * #Hard #Greedy #PriorityQueue
 */
public class CourseScheduleIII {
    public static void main(String[] args) {
        CourseScheduleIII sol = new CourseScheduleIII();
        System.out.println(sol.scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}})); // 3
        System.out.println(sol.scheduleCourse(new int[][]{{1, 2}, {2, 3}})); // 2
        System.out.println(sol.scheduleCourse(new int[][]{{5, 5}, {4, 6}, {2, 6}})); // 2
    }

    public int scheduleCourse(int[][] courses) {
        //[[5, 5], [4, 6], [2, 6]]
        // q: 4 2
        // sum: 6
        if (courses == null || courses.length == 0) return 0;
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int sum = 0;
        for (int[] course : courses) {
            if (sum + course[0] <= course[1]) {
                sum += course[0];
                maxQ.add(course[0]);
            } else if (!maxQ.isEmpty() && maxQ.peek() > course[0]) {
                sum -= maxQ.poll();
                sum += course[0];
                maxQ.add(course[0]);
            }
        }
        return maxQ.size();
    }
}
