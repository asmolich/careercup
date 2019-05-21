import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * InterviewBit. Triplets With Sum Between Given Range.
 *
 * https://www.interviewbit.com/problems/triplets-with-sum-between-given-range/
 */
public class TripletsWithSumBetweenGivenRange {

    public static void main(String[] args) {
        final String[] data = {
            "0.6", "0.7", "0.8", "1.2", "0.4"
        };
        System.out.println(Arrays.toString(data));
        System.out.println("*************");
        System.out.println(hasTripletsBruteForce(data));
        System.out.println(hasTripletsTree(data));
        System.out.println(hasTripletsSorting(data));
        System.out.println(hasTripletsBuckets(data));
        System.out.println(hasTriplets3Pointer(data));
    }

    private static boolean hasTriplets3Pointer(String[] arr) {
        if (arr == null || arr.length < 3) return false;

        double a = Double.parseDouble(arr[0]);
        double b = Double.parseDouble(arr[1]);
        double c = Double.parseDouble(arr[2]);

        for (int i = 3, length = arr.length; i < length; i++) {
            if ((a + b + c) > 1.0 && (a + b + c) < 2.0) {
                return true;
            }

            double d = Double.parseDouble(arr[i]);

            if ((a + b + c) >= 2.0) {
                if (a > b && a > c) {
                    a = d;
                } else if (b > c) {
                    b = d;
                } else {
                    c = d;
                }
            } else {
                if (a < b && a < c) {
                    a = d;
                } else if (b < c) {
                    b = d;
                } else {
                    c = d;
                }
            }
        }

        return (a + b + c) > 1.0 && (a + b + c) < 2.0;
    }

    private static boolean isInInterval(double d, int interval) {
        return ((interval == 1) && (d > 0 && d < 2.0 / 3.0)) ||
            ((interval == 2) && (d >= 2.0 / 3.0 && d < 1.0)) ||
            ((interval == 3) && (d >= 1.0 && d < 2.0));
    }

    // O(n)
    private static boolean hasTripletsBuckets(String[] arr) {
        if (arr == null || arr.length < 3) return false;

        double[] a = Stream.of(arr)
            .mapToDouble(Double::parseDouble)
            .toArray();

        double max1A = Double.NEGATIVE_INFINITY;
        double max2A = Double.NEGATIVE_INFINITY;
        double max3A = Double.NEGATIVE_INFINITY;
        double max1B = Double.NEGATIVE_INFINITY;

        double min1A = Double.POSITIVE_INFINITY;
        double min2A = Double.POSITIVE_INFINITY;
        double min1B = Double.POSITIVE_INFINITY;
        double min2B = Double.POSITIVE_INFINITY;
        double min1C = Double.POSITIVE_INFINITY;

        for (double d : a) {
            final boolean inFirst = isInInterval(d, 1);
            final boolean inSecond = isInInterval(d, 2);
            if (max1A < d && inFirst) {
                max3A = max2A;
                max2A = max1A;
                max1A = d;
            } else if (max2A < d && inFirst) {
                max3A = max2A;
                max2A = d;
            } else if (max3A < d && inFirst) {
                max3A = d;
            } else if (max1B < d && inSecond) {
                max1B = d;
            }

            if (min1A > d && inFirst) {
                min2A = min1A;
                min1A = d;
            } else if (min2A > d && inFirst) {
                min2A = d;
            } else if (min1B > d && inSecond) {
                min2B = min1B;
                min1B = d;
            } else if (min2B > d && inSecond) {
                min2B = d;
            } else {
                final boolean inThird = isInInterval(d, 3);
                if (min1C > d && inThird) {
                    min1C = d;
                }
            }
        }

        if (max1A + max2A + max3A > 1.0) {
            return true;
        } else if ((max1A + max2A + max1B > 1.0) && (max1A + max2A + max1B < 2.0)) {
            return true;
        } else if ((min1A + min2A + min1B > 1.0) && (min1A + min2A + min1B < 2.0)) {
            return true;
        } else if (min1A + min2A + min1C < 2.0) {
            return true;
        } else if (min1A + min1B + min2B < 2.0) {
            return true;
        } else return min1A + min1B + min1C < 2.0;
    }

    // O(n * log n)
    private static boolean hasTripletsSorting(String[] arr) {
        if (arr == null || arr.length < 3) return false;

        double[] a = Stream.of(arr)
            .mapToDouble(Double::parseDouble)
            .sorted()
            .toArray();

        int length = arr.length;
        int i = 0;
        int j = 1;
        int k = 2;

        double sum = a[i] + a[j] + a[k];
        do {
            if (1 < sum && sum < 2) {
                return true;
            } else if (sum > 2) return false;

            if (i < j - 1) {
                sum -= a[i];
                i++;
                sum += a[i];
            } else if (j < k - 1) {
                sum -= a[j];
                j++;
                sum += a[j];
            } else if (k < length - 1) {
                sum -= a[k];
                k++;
                sum += a[k];
            } else break;
        }
        while (i < length - 2 && j < length - 1 && k < length);
        return false;
    }

    // O(n^2 * log n)
    private static boolean hasTripletsTree(String[] arr) {
        if (arr == null || arr.length < 3) return false;

        TreeMap<Double, Set<Integer>> map = new TreeMap<>();
        for (int i = 0, length = arr.length; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    double sum = Double.parseDouble(arr[i]) + Double.parseDouble(arr[j]);
                    map.put(sum, Set.of(i, j));
                }
            }
        }

        for (int i = 0, length = arr.length; i < length; i++) {
            double d = Double.parseDouble(arr[i]);
            // 1 - d < map.key < 2 - d
            Map.Entry<Double, Set<Integer>> higher = map.higherEntry(1 - d);
            if (higher != null && !higher.getValue().contains(i)) {
                Map.Entry<Double, Set<Integer>> lower = map.lowerEntry(2 - d);
                if (lower != null && !lower.getValue().contains(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    // O(n^3)
    private static boolean hasTripletsBruteForce(String[] arr) {
        if (arr == null || arr.length < 3) return false;

        for (int i = 0, length = arr.length; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    if (i != j && j != k && i != k) {
                        double sum = Double.parseDouble(arr[i]) + Double.parseDouble(arr[j]) + Double.parseDouble(arr[k]);
                        if (1 < sum && sum < 2) return true;
                    }
                }
            }
        }
        return false;
    }
}
