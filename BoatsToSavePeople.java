import java.util.Arrays;

/**
 * LeetCode
 * 881. Boats to Save People
 * https://leetcode.com/problems/boats-to-save-people/
 * #Medium #Greedy
 */
public class BoatsToSavePeople {
    public static void main(String[] args) {
        BoatsToSavePeople sol = new BoatsToSavePeople();
        System.out.println(sol.numRescueBoats(new int[]{1, 2}, 3)); // 1
        System.out.println(sol.numRescueBoats(new int[]{3, 2, 2, 1}, 3)); // 3
        System.out.println(sol.numRescueBoats(new int[]{3, 5, 3, 4}, 5)); // 4
        System.out.println(sol.numRescueBoats(new int[]{3, 2, 3, 2, 2}, 6)); // 3
    }

    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) return 0;

        Arrays.sort(people);

        int n = people.length;
        int lo = 0;
        int hi = n - 1;
        int count = 0;
        while (lo <= hi) {
            int w = people[hi--];
            if (lo <= hi && w + people[hi] <= limit) {
                hi--;
            } else if (lo <= hi && w + people[lo] <= limit) {
                lo++;
            }
            //System.out.println("w = " + w);
            count++;
        }
        return count;
    }
}
