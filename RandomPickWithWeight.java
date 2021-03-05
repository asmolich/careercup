import java.util.Arrays;
import java.util.Random;

/**
 * LeetCode
 * 528. Random Pick with Weight
 * https://leetcode.com/problems/random-pick-with-weight/
 * #Medium #BinarySearch #Random
 */
public class RandomPickWithWeight {
    public static void main(String[] args) {
        RandomPickWithWeight weightedRandom = new RandomPickWithWeight(new int[]{1});
        System.out.println(weightedRandom.pickIndex()); // 0
        weightedRandom = new RandomPickWithWeight(new int[]{1, 3});
        System.out.println(weightedRandom.pickIndex()); // 1
        System.out.println(weightedRandom.pickIndex()); // 1
        System.out.println(weightedRandom.pickIndex()); // 1
        System.out.println(weightedRandom.pickIndex()); // 1
        System.out.println(weightedRandom.pickIndex()); // 0
        // Since this is a randomization problem, multiple answers are allowed
        // so the following outputs can be considered correct:
        // [null,1,1,1,1,0]
        // [null,1,1,1,1,1]
        // [null,1,1,1,0,0]
        // [null,1,1,1,0,1]
        // [null,1,0,1,0,0]
    }

    private final int[] prefixSumWeights;
    private final Random random = new Random();

    public RandomPickWithWeight(int[] weights) {
        int n = weights.length;
        prefixSumWeights = new int[n];
        prefixSumWeights[0] = weights[0];
        for (int i = 1; i < n; i++) {
            prefixSumWeights[i] = prefixSumWeights[i - 1] + weights[i];
        }
    }

    public int pickIndex() {
        int n = prefixSumWeights.length;
        int r = random.nextInt(prefixSumWeights[n - 1] + 1);
        // [1, 3]
        // [1, 4]
        //
        int i = Arrays.binarySearch(prefixSumWeights, r);
        if (i < 0) i = ~i;
        return i;
    }
}
/*
Common Sense O(N) constructor, O(1) pickIndex
def __init__(self, w: List[int]):
    total = sum(w)
    self.opts = []

    for i in range(len(w)):
        c = (w[i]*100) // total
        if c == 0:
            self.opts.append(i)
            continue
        while c:
            self.opts.append(i)
            c -= 1


def pickIndex(self) -> int:
    return self.opts[random.randint(0, len(self.opts) - 1)]

==================
Prefix sum + binary search: init time O(n), search time O(log(n)), space O(n)
# init time O(n), search time O(log(n)), space O(n)
def __init__(self, w):
  for i in range(1, len(w)):
    w[i] = w[i-1] + w[i]
  self.psum = w

def pickIndex(self):
  n = random.randrange(self.psum[-1])
  return bisect.bisect(self.psum, n)
 */