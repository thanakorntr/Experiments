package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/longest-common-increasing-subsequence-lcs-lis/
 *
 * Longest Common Increasing Subsequence (LCS + LIS)
 Prerequisites : LCS, LIS

 Given two arrays, find length of the longest common increasing subsequence [LCIS] and print one of such sequences (multiple sequences may exist)

 Suppose we consider two arrays –
 arr1[] = {3, 4, 9, 1} and
 arr2[] = {5, 3, 8, 9, 10, 2, 1}

 Our answer would be {3, 9} as this is the longest common subsequence which is increasing also.

 *
 * Created by Thanakorn on 6/22/16.
 */
public class LongestCommonIncreasingSubsequence {

    public static void main(String[] args) {

        int[] nums1 = new int[]{3,4,9,1};
        int[] nums2 = new int[]{5,3,8,9,10,2,1};
        System.out.println(findLCIS(nums1, nums2).toString());  // 3,9

    }

    private static List<Integer> findLCIS(int[] nums1, int[] nums2) {

        Map<Integer, Map<Integer, List<Integer>>> memo = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (!memo.containsKey(i)) {
                    memo.put(i, new HashMap<>());
                }
                memo.get(i).put(j, null);
            }
        }

        Map<Integer, Integer> nums2IndiceMap = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            nums2IndiceMap.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            if (nums2IndiceMap.containsKey(nums1[i])) {
                findLCISHelper(nums1, i, nums2, nums2IndiceMap.get(nums1[i]), memo);
            }
        }

        List<Integer> lcis = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (memo.get(i).get(j) != null && memo.get(i).get(j).size() > lcis.size()) {
                    lcis = memo.get(i).get(j);
                }
            }
        }

        return lcis;
    }


    private static void findLCISHelper(int[] nums1, int start1, int[] nums2, int start2,
                                       Map<Integer, Map<Integer, List<Integer>>> memo) {

        if (memo.get(start1).get(start2) != null) {
            return;
        }

        List<Integer> lcis = new ArrayList<>();
        lcis.add(nums1[start1]);

        List<Integer> longestSubProblem = new ArrayList<>();
        Map<Integer, Integer> nums2IndiceMap = new HashMap<>();
        for (int i = nums2.length - 1; i > start2; i--) {
            nums2IndiceMap.put(nums2[i], i);
        }

        for (int i = start1 + 1; i < nums1.length; i++) {
            if (nums1[i] > nums1[start1]) {
                if (nums2IndiceMap.containsKey(nums1[i])) {
                    int j = nums2IndiceMap.get(nums1[i]);
                    findLCISHelper(nums1, i, nums2, j, memo);
                    if (memo.get(i).get(j).size() > longestSubProblem.size()) {
                        longestSubProblem = memo.get(i).get(j);
                    }
                }
            }
        }

        lcis.addAll(longestSubProblem);
        memo.get(start1).put(start2, lcis);
    }

}
