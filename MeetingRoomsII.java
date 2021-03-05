import java.util.TreeMap;

/**
 * LeetCode
 * 253. Meeting Rooms II
 * https://leetcode.com/problems/meeting-rooms-ii/
 * https://ttzztt.gitbooks.io/lc/content/heap/meeting-room.html
 * #Medium
 */
public class MeetingRoomsII {
    public static void main(String[] args) {
        MeetingRoomsII sol = new MeetingRoomsII();
        System.out.println(sol.canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}})); // 2
        System.out.println(sol.canAttendMeetings(new int[][]{{7, 10}, {2, 4}})); // 1
    }

    public int canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            tree.put(start, tree.getOrDefault(start, 0) + 1);
            tree.put(end, tree.getOrDefault(end, 0) - 1);
        }
        int max = 0;
        int count = 0;
        for (var entry : tree.entrySet()) {
            count += entry.getValue();
            max = Math.max(max, count);
        }
        return max;
    }
}