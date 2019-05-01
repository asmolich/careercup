import java.util.ArrayList;
import java.util.Arrays;

public class NumRange {
    public static void main(String[] args) {
        NumRange nr = new NumRange();
        // 26
        System.out.println(nr.numRange(new ArrayList<>(Arrays.asList(26, 33, 57, 42, 1, 77, 66, 81, 57, 68, 98, 9, 54, 36, 58, 20, 100, 16, 88, 57, 62, 41, 32, 5)), 65, 121));
    }

    public int numRange(ArrayList<Integer> a, int b, int c) {
        System.out.println(a);
        System.out.println("[" + b + ',' + c + ']');

        int count = 0;
        if (a == null || a.isEmpty() || b > c) return count;

        int sum = 0;
        int j = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
            if (sum < b) {
                continue;
            }
            if (sum > c) {
                while (sum > c && j <= i) {
                    sum -= a.get(j++);
                }
            }
            if (inRange(sum, b, c)) {
                int k = j;
                int localSum = sum;
                while (k <= i) {
                    if (inRange(localSum, b, c)) {
                        count++;
                        System.out.println("aj=" + a.get(j) + ", ak=" + a.get(k) + ", ai=" + a.get(i) + ", sum=" + localSum + ", cnt=" + count);
                    } else
                        break;
                    localSum -= a.get(k++);
                }
            }
        }

        return count;
    }

    private boolean inRange(int a, int b, int c) {
        return a >= b && a <= c;
    }
}
