/**
 * LeetCode
 * 27. Remove Element
 * https://leetcode.com/problems/remove-element/
 * #Easy
 */
public class RemoveElement {
    public static void main(String[] args) {
        RemoveElement sol = new RemoveElement();
        System.out.println(sol.removeElement(new int[]{3, 2, 2, 3}, 3)); // 2
        System.out.println(sol.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2)); // 5
        System.out.println(sol.removeElement(new int[]{0, 1, 2, 3, 4}, 5)); // 5
        System.out.println(sol.removeElement(new int[]{1, 1, 1, 1, 1, 1}, 1)); // 0
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int idx = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val) {
                if (idx == -1) {
                    idx = i;
                }
            } else {
                if (idx != -1) {
                    nums[idx++] = nums[i];
                }
            }
        }
        return idx == -1 ? n : idx;
    }
}
