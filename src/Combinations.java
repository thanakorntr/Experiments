import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 6/6/15.
 */
public class Combinations {

    public static void main(String[] args) {
        printListOfList(combine(4,2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        if (k > n || n <= 0) {
            List<Integer> emptySet = new ArrayList<>();
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(emptySet);
            return ans;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }

        return combineHelper(nums, 0, k);
    }

    public static List<List<Integer>> combineHelper(int[] nums, int startIndex, int k) {

        if (k == 0 || startIndex >= nums.length) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> empty = new ArrayList<>();
            ans.add(empty);
            return ans;
        }

        if (startIndex + k == nums.length) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> subAns = new ArrayList<>();
            for (int i = startIndex; i < startIndex + k; i++) {
                subAns.add(nums[i]);
            }
            ans.add(subAns);
            return ans;
        }

        List<List<Integer>> subAns1 = combineHelper(nums, startIndex+1, k);
        List<List<Integer>> subAns2 = combineHelper(nums, startIndex+1, k-1);

        List<List<Integer>> ans = new ArrayList<>(subAns1);
        for (List<Integer> subsubAns2 : subAns2) {
            subsubAns2.add(0, nums[startIndex]);
            ans.add(subsubAns2);
        }

        return ans;
    }

    public static void printListOfList(List<List<Integer>> list) {
        for (List<Integer> l : list) {
            System.out.println(l.toString());
        }
    }
}
