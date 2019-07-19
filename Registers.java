import java.util.Arrays;

public class Registers {
    public static void main(String[] args) {
        System.out.println(timeToProcessQueue(new int[]{
            2, 3, 4, 7, 4, 3
        }, 3)); // 9
        System.out.println(timeToProcessQueue(new int[]{
            2, 3, 4, 7
        }, 2)); // 10
    }

    private static int timeToProcessQueue(int[] arr, int n) {
        if (arr == null || arr.length == 0 || n < 1) return -1;

        if (n < arr.length) {
            int res = 0;
            int[] registers = Arrays.copyOfRange(arr, 0, n);
            int min = findMin(registers);

            res += arr[min];

            for (int i = n; i < arr.length; i++) {

            }
            return res + arr[findMax(registers)];
        }
        else return arr[findMax(arr)];
    }

    private static int findMin(int[] arr) {
        int min = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[min]) min = i;
        }
        return min;
    }

    private static int findMax(int[] arr) {
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[max]) max = i;
        }
        return max;
    }
}
