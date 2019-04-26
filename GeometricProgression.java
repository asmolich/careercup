import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeometricProgression {

    public static void main(String[] args) {
        System.out.println(countTriplets(Arrays.asList(1L, 2L, 2L, 4L), 2L));
        System.out.println(countTriplets(Arrays.asList(5L, 5L, 5L, 5L, 5L), 1L));
        System.out.println(countTriplets(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L), 3L));
    }

    private static long countTriplets(List<Long> input, long r) {
        if (input == null || input.size() < 3) return 0L;

        long tripletsCount = 0L;
        Map<Long, Long> mp2 = new HashMap<>();
        Map<Long, Long> mp3 = new HashMap<>();
        for (Long val : input) {
            if (mp3.containsKey(val)) tripletsCount += mp3.get(val);

            final long next = val * r;
            if (mp2.containsKey(val)) // if val is a second element in a triplet
                // increment third element occurrence in mp3
                mp3.put(next, mp3.getOrDefault(next, 0L) + mp2.get(val));

            // increment second element occurrence in mp2
            mp2.put(next, mp2.getOrDefault(next, 0L) + 1);

            //System.out.println("val = " + val + ", mp2 = " + mp2 + ", mp3 = " + mp3);
        }

        return tripletsCount;
    }
}


// tree traversals
// tree structures
// recursive -> iterative using stacks or queues
// hash maps, stacks, queues
// strings (isPalindrome, isAnagram)
