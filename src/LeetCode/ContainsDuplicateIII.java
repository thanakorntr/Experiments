package LeetCode;

import java.util.TreeSet;

/**
 * Created by Thanakorn on 9/28/15.
 * <p>
 * Given an array of integers, find out whether there are two distinct indices i and j in
 * the array such that the difference between nums[i] and nums[j] is at most t and the difference
 * between i and j is at most k.
 */
public class ContainsDuplicateIII {

    public static void main(String[] args) {


    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        TreeSet<Long> treeSet = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {

            if (i > k) {  // exit the first window
                treeSet.remove((long)nums[i-k-1]);
            }

            Long floor = treeSet.floor((long)nums[i]);
            if (floor != null && nums[i] - floor <= t) {
                return true;
            }
            Long ceiling = treeSet.ceiling((long)nums[i]);
            if (ceiling != null && ceiling - nums[i] <= t) {
                return true;
            }

            treeSet.add((long)nums[i]);
        }

        return false;
    }
}
