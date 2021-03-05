import java.util.Arrays;
import java.util.List;

/**
 * LeetCode
 * 339. Nested List Weight Sum
 * https://www.cnblogs.com/grandyang/p/5340305.html
 * <p>
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1.
 * <p>
 * Example 2:
 * Input: [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class NestedListWeightSum {
    public static void main(String[] args) {
        NestedListWeightSum sol = new NestedListWeightSum();
        NestedInteger[] nestedList = new NestedInteger[]{ // [[1,1],2,[1,1]]
                new NestedInteger(1, 1),
                new NestedInteger(2),
                new NestedInteger(1, 1)
        };
        System.out.println(Arrays.toString(nestedList));
        System.out.println(sol.depthSum(nestedList));

//        nestedList = new NestedInteger[]{ // [1,[4,[6]]]
//                new NestedInteger(1, new NestedInteger(4, new NestedInteger(6)),
//        };
        System.out.println(Arrays.toString(nestedList));
        System.out.println(sol.depthSum(nestedList));
    }

    public int depthSum(NestedInteger[] nestedList) {
        return depthSum0(Arrays.asList(nestedList), 1);
    }

    private int depthSum0(List<NestedInteger> nestedList, int level) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) sum += ni.getInteger() * level;
            else sum += depthSum0(ni.getList(), level + 1);
        }
        return sum;
    }
}
