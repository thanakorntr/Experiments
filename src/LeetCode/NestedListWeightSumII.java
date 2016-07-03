package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom
 up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

 Example 2:
 Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1;
 1*3 + 4*2 + 6*1 = 17)
 *
 * Created by Thanakorn on 7/3/16.
 */
public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        List<List<Integer>> intLists = new ArrayList<>();
        depthSumInverseHelper(nestedList, intLists, 0);

        int sum = 0;
        int weight = 1;
        for (int i = intLists.size() - 1; i >= 0; i--) {
            for (int val : intLists.get(i)) {
                sum += weight * val;
            }
            weight++;
        }

        return sum;
    }

    private void depthSumInverseHelper(List<NestedInteger> nestedIntegers,
                                       List<List<Integer>> lists,
                                       int depth) {
        if (lists.size() == depth) {
            lists.add(new ArrayList<>());
        }

        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                lists.get(depth).add(nestedInteger.getInteger());
            } else {
                depthSumInverseHelper(nestedInteger.getList(), lists, depth + 1);
            }
        }
    }

}
