import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 6/6/15.
 */
public class Subsets {

    public static void main(String[] args) {

        int[] nums = new int[] {1,2,3};

        printSubsets(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            List<Integer> emptySet = new ArrayList<>();
            List<List<Integer>> emptySubSet = new ArrayList<>();
            emptySubSet.add(emptySet);
            return emptySubSet;
        }

        Arrays.sort(nums);
        return subsetsHelper(nums, 0);
    }

    public static List<List<Integer>> subsetsHelper(int[] nums, int startIndex) {
        if (nums == null || nums.length == 0 || startIndex >= nums.length) {
            List<Integer> emptySet = new ArrayList<>();
            List<List<Integer>> emptySubSet = new ArrayList<>();
            emptySubSet.add(emptySet);
            return emptySubSet;
        }

        List<List<Integer>> nextSubSets = subsetsHelper(nums, startIndex + 1);

        List<List<Integer>> subsets = new ArrayList<>(nextSubSets);


        for (List<Integer> nextSubSet : nextSubSets) {
            List<Integer> tempSubSet = new ArrayList<>(nextSubSet);
            tempSubSet.add(0, nums[startIndex]);
            subsets.add(tempSubSet);
        }

        return subsets;
    }

    public static void printSubsets(List<List<Integer>> subsets) {
        for (List<Integer> subset: subsets) {
            System.out.println(subset.toString());
        }
    }

}
