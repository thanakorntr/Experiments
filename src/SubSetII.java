import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 7/3/15.
 */
public class SubSetII {

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        List<List<Integer>> subsets = subsetsWithDup(nums);
        subsets.forEach(x -> System.out.println(x.toString()));

    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return subsetsWithDupHelper(nums, 0);
    }

    public static List<List<Integer>> subsetsWithDupHelper(int[] nums, int startIndex) {
        List<List<Integer>> subsets = new ArrayList<>();
        if (startIndex == nums.length) {
            subsets.add(new ArrayList<>());
            return subsets;
        }

        List<List<Integer>> nextSubSets = subsetsWithDupHelper(nums, startIndex+1);
        for (List<Integer> l : nextSubSets) {
            // add nums[startIndex]
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[startIndex]);
            temp.addAll(l);
            if (!subsets.contains(temp)) {
                subsets.add(temp);
            }

            // do not add nums[startIndex]
            if (!subsets.contains(l)) {
                subsets.add(l);
            }
        }

        return subsets;
    }
}
