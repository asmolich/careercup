import java.util.Arrays;

/**
 * Knuth's Shuffling Algorithm
 * https://www.youtube.com/watch?v=54rMYC2pEtw
 */
public class Shuffle {
    public static void main(String[] args) {
        final int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffle(data);
        System.out.println(Arrays.toString(data));
    }

    private static void shuffle(int[] arr) {
        if (arr == null || arr.length == 0) return;

        for (int i = 1; i < arr.length; i++) {
            int r = (int) (Math.random() * i);

            swap(arr, i, r);
        }
    }

    private static void swap(int[] arr, int i, int r) {
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
    }
}
