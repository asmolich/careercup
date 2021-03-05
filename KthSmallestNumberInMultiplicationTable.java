import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode
 * 668. Kth Smallest Number in Multiplication Table
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 */
public class KthSmallestNumberInMultiplicationTable {
    public static void main(String[] args) {
//        System.out.println(findKthNumber(2, 2, 1)); // 1
//        System.out.println(findKthNumber(2, 2, 2)); // 2
//        System.out.println(findKthNumber(2, 2, 3)); // 2
//        System.out.println(findKthNumber(2, 2, 4)); // 4
//        System.out.println(findKthNumber(3, 3, 5)); // 3
//        System.out.println(findKthNumber(2, 3, 6)); // 6
        System.out.println(findKthNumber(
                9895,
                28405,
                100787757));
    }

    /*  m = 5, n = 12
     *      1  2  3  4  5  6  7  8  9 10 11 12
     *    \-----------------------------------
     *  1 | 1  2  3  4  5  6  7  8  9 10 11 12
     *  2 | 2  4  6  8 10 12 14 16 18 20 22 24
     *  3 | 3  6  9 12 15 18 21 24 27 30 33 36
     *  4 | 4  8 12 16 20 24 28 32 36 40 44 48
     *  5 | 5 10 15 20 25 30 35 40 45 50 55 60
     *
     *  k = 7: 4
     *  k = 11: 6
     *  k = 18: 9
     *  k = 23: 11
     *  k = 28: 14
     *
     */
    private static class Cell {
        int row = 0;
        int col = 0;
        int value = 0;
//        int value () {return row * col;}
    }

    private static int findKthNumber(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) return -1;
        if (k == 1) return 1;

        int p = Math.min(m, n);
        int q = Math.max(m, n);

        // p * q
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        // initial column
        for (int i = 1; i <= p; i++) {
            Cell cell = new Cell();
            cell.row = i;
            cell.col = 1;
            cell.value = i;
            pq.add(cell);
        }

        int count = 0;
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            if (count == k - 1) return cell.value;
            if (cell.col < q) {
                cell.value += cell.row;
                cell.col += 1;
                pq.add(cell);
            }
            count++;
        }
        return -1;
    }
}
