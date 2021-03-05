import java.util.Arrays;

/**
 * LeetCode
 * 493. Reverse Pairs
 * https://leetcode.com/problems/reverse-pairs/
 * #Hard #MergeSort
 */
public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs sol = new ReversePairs();
        System.out.println(sol.reversePairs(new int[]{1, 3, 2, 3, 1})); // 2
        System.out.println(sol.reversePairs(new int[]{2, 4, 3, 5, 1})); // 3
        System.out.println(sol.reversePairs(new int[]{5, 4, 3, 2, 1})); // 4
        System.out.println(sol.reversePairs(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE})); // 0
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int i = reversePairs0(nums, 0, nums.length - 1, new int[nums.length]);
        System.out.println(Arrays.toString(nums));
        return i;
    }

    private int reversePairs0(int[] nums, int lo, int hi, int[] aux) {
        if (hi <= lo) return 0;

        int mid = (lo + hi) >>> 1;
        int res = 0;
        res += reversePairs0(nums, lo, mid, aux);
        res += reversePairs0(nums, mid + 1, hi, aux);
        res += reversePairsCenter(nums, lo, mid, hi, aux);
        return res;
    }

    // Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
    // You need to return the number of important reverse pairs in the given array.
    // p1-> 1 2 5 9
    // p2-> 3 6 8
    private int reversePairsCenter(int[] nums, int lo, int mid, int hi, int[] aux) {
        if (hi + 1 - lo >= 0) System.arraycopy(nums, lo, aux, lo, hi + 1 - lo);

        int p1 = lo;
        int p2 = mid + 1;
        int count = countPairs(nums, lo, mid, hi);
        for (int i = lo; i <= hi; i++) {
            if (p1 <= mid && p2 <= hi) {
                if (aux[p1] <= aux[p2]) {
                    nums[i] = aux[p1++];
                } else {
                    nums[i] = aux[p2++];
                }
            } else if (p1 <= mid) {
                nums[i] = aux[p1++];
            } else if (p2 <= hi) {
                nums[i] = aux[p2++];
            }
        }
        return count;
    }

    private int countPairs(int[] arr, int lo, int mid, int hi) {
        int count = 0;
        int p2 = mid + 1;
        for (int i = lo; i <= mid; i++) {
            while (p2 <= hi && arr[i] > 2L * arr[p2]) {
                p2++;
            }
            count += p2 - (mid + 1);
        }
        return count;
    }

/*
void update(vector<int>& BIT, int index, int val)
{
    while (index > 0) {
        BIT[index] += val;
        index -= index & (-index);
    }
}

int query(vector<int>& BIT, int index)
{
    int sum = 0;
    while (index < BIT.size()) {
        sum += BIT[index];
        index += index & (-index);
    }
    return sum;
}
int reversePairs(vector<int>& nums)
{
    int n = nums.size();
    vector<int> nums_copy(nums);

    sort(nums_copy.begin(), nums_copy.end());

    vector<int> BITS(n + 1, 0);
    int count = 0;
    for (int i = 0; i < n; i++) {
        count += query(BITS, lower_bound(nums_copy.begin(), nums_copy.end(), 2LL * nums[i] + 1) - nums_copy.begin() + 1);
        update(BITS, lower_bound(nums_copy.begin(), nums_copy.end(), nums[i]) - nums_copy.begin() + 1, 1);
    }
    return count;
}
*/
}
