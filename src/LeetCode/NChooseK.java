package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 9/27/15.
 */
public class NChooseK {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4};
        int k = 2;
        List<List<Integer>> ans = nChooseK(nums, k);
        for (List<Integer> c : ans) {
            System.out.println(c.toString());
        }

    }

    private static List<List<Integer>> nChooseK(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        nChooseKHelper(nums, k, 0, cur, ans);
        return ans;
    }

    private static void nChooseKHelper(int[] nums, int k, int startIndex, List<Integer> cur, List<List<Integer>> ans) {
        int numRemaining = nums.length - startIndex;
        if (numRemaining == k) {
            for (int i = startIndex; i < nums.length; i++) {
                cur.add(nums[i]);
            }
            ans.add(cur);
            return;
        }

        if (k == 0) {
            ans.add(cur);
            return;
        }

        if (startIndex >= nums.length) {
            return;
        }

        List<Integer> tmp = new ArrayList<>(cur);
        cur.add(nums[startIndex]);
        nChooseKHelper(nums, k-1, startIndex+1, cur, ans);
        nChooseKHelper(nums, k, startIndex+1, tmp, ans);
    }
}
