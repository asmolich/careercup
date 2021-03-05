/**
 * Hacker Rank
 * Fair Rations
 * https://www.hackerrank.com/challenges/fair-rations/problem
 */
public class FairRations {
    public static void main(String[] args) {
        System.out.println(fairRations(new int[]{4, 7, 3, 2, 5, 9, 12})); // 4
        System.out.println(fairRations(new int[]{7, 7, 7})); // NO
        System.out.println(fairRations(new int[]{1, 2, 2, 2, 2, 3})); // 10
    }

    private static int fairRations(int[] a) {
        if (a == null || a.length == 0) return 0;

        int count = 0;
        int odds = 0;

        //for (int val : a) if (val % 2 == 1) odds++;

        int firstOdd = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 1) {
                odds++;
                if (firstOdd == -1) {
                    firstOdd = i;
                } else {
                    // System.out.println("firstOdd = " + firstOdd + ", i = " + i);
                    count += (i - firstOdd) << 1;
                    firstOdd = -1;
                }
            }
        }

        if (odds % 2 == 1) {
            System.out.println("NO");
            return 0;
        }

        return count;
    }
}
