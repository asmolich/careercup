import java.util.Arrays;

/**
 * LeetCode
 * Meeting Rooms III
 * https://leetcode.com/discuss/interview-question/613816/Google-or-Onsite-or-Meeting-Rooms-3
 * #Hard #RMQ
 * #incomplete
 */
public class MeetingRoomsIII {
    public static void main(String[] args) {
        MeetingRoomsIII sol = new MeetingRoomsIII();
//        System.out.println(Arrays.toString(sol.canAddMeetings(new int[][]{{1, 2}, {4, 5}, {8, 10}}, 1, new int[][]{{2, 3}, {3, 4}}))); // [true, true]
        System.out.println(Arrays.toString(sol.canAddMeetings(new int[][]{{1, 2}, {4, 5}, {8, 10}}, 1, new int[][]{{4, 5}, {5, 6}}))); // [false, true]
//        System.out.println(Arrays.toString(sol.canAddMeetings(new int[][]{{1, 3}, {4, 6}, {6, 8}, {9, 11}, {6, 9}, {1, 3}, {4, 10}}, 3, new int[][]{{1, 9}, {2, 6}, {7, 9}, {3, 5}, {3, 9}, {2, 4}, {7, 10}, {5, 9}, {3, 10}, {9, 10}}))); // [false, true, false, true, false, true, false, false, false, true]
    }

    public boolean[] canAddMeetings(int[][] calendar, int rooms, int[][] queries) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] interval : calendar) {
            min = Math.min(min, interval[0]);
            max = Math.max(max, interval[1]);
        }
        int size = max - min + 1;
        int p = 32 - Integer.numberOfLeadingZeros(size);
        System.out.println("size = " + size + ", " + Integer.toBinaryString(size));
        System.out.println("p = " + p);
        int[][] sparseTable = new int[p + 1][size + 1];
        for (int[] interval : calendar) {
            sparseTable[0][interval[0] - min]++;
            sparseTable[0][interval[1] - min + 1]--;
        }
        int count = 0;
        for (int i = 0; i <= size; i++) {
            count += sparseTable[0][i];
            sparseTable[0][i] = count;
        }

        System.out.println(Arrays.toString(sparseTable[0]));
        System.out.println("=================================");
        for (int i = 1; i <= p; i++) {
            int step = 1 << (i - 1);
            //System.out.println("step = " + step);
            for (int j = 0; j + step <= size; j++) {
                sparseTable[i][j] = Math.min(sparseTable[i - 1][j], sparseTable[i - 1][j + step]);
            }
        }
        for (int i = 0; i <= p; i++) {
            System.out.println("1 << i = " + (1 << (i - 1)));
            System.out.println(Arrays.toString(sparseTable[i]));
        }

        // answer queries
        int q = queries.length;
        boolean[] res = new boolean[q];
        for (int i = 0; i < q; i++) {
            int start = queries[i][0] - min;
            int end = queries[i][1] - min;

            int lvl = Integer.highestOneBit(start ^ end);
            System.out.println("from " + start + " to " + end + ", lvl = " + lvl);
            int occupied = Math.min(sparseTable[lvl][start], sparseTable[lvl][end]);
            System.out.println("occupied = " + occupied);
            res[i] = occupied < rooms;
        }
        return res;
    }
}