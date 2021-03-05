import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode
 * 252. Meeting Rooms
 * https://leetcode.com/problems/meeting-rooms/
 * https://ttzztt.gitbooks.io/lc/content/sort/meeting-rooms.html
 * #Easy
 */
public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms sol = new MeetingRooms();
        System.out.println(sol.canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}})); // false
        System.out.println(sol.canAttendMeetings(new int[][]{{7, 10}, {2, 4}})); // true
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return false;

        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]).thenComparing(a -> a[1]));

        int n = intervals.length;
        for (int i = 1; i < n; i++) {
            if (intersect(intervals[i - 1], intervals[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean intersect(int[] i1, int[] i2) {
        return i2[0] < i1[1];
    }
}
