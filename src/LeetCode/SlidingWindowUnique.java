package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 9/20/15.
 *
 * http://www.geeksforgeeks.org/microsoft-interview-experience-set-70-on-campus-for-idc-and-it/
 *
 * Using constant ( O(1) ) memory – storing the pointers etc not allowed
 Second question was, given an array and a window size that is sliding along the array, find the sum of the count of unique elements in each window.
 For example, if the array were 1 2 1 3 3 and window size was three

 First window -      1 2 1 - only two unique = 1
 Second window-  2 1 3 - all unique = 3
 Third window -     1 3 3 - only one unique = 1

 Total = 1+3+1 = 5
 Which was to be returned.
 */
public class SlidingWindowUnique {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,1,3,3};
        int windowSize = 3;
        System.out.println(findSumUniqueInWindows(nums, windowSize)); // 5

        nums = new int[]{5,5,5,5,1,1};
        System.out.println(findSumUniqueInWindows(nums, windowSize)); // 2
    }

    private static int findSumUniqueInWindows(int[] nums, int windowSize) {
        Map<Integer,Integer> intMap = new HashMap<>();
        int sumUnique = 0;
        int curUniqueNum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i < windowSize) {
                if (!intMap.containsKey(nums[i])) {
                    intMap.put(nums[i], 1);
                    curUniqueNum++;
                } else {
                    if (intMap.get(nums[i]) == 1) {
                        curUniqueNum--;
                    }
                    intMap.put(nums[i], intMap.get(nums[i]) + 1);
                }
            } else {
                sumUnique += curUniqueNum;
                int firstNumPrevWindow = nums[i - windowSize];
                if (intMap.get(firstNumPrevWindow) == 1) {
                    intMap.remove(firstNumPrevWindow);
                    curUniqueNum--;
                } else {
                    if (intMap.get(firstNumPrevWindow) == 2) {
                        curUniqueNum++;
                    }
                    intMap.put(firstNumPrevWindow, intMap.get(firstNumPrevWindow) - 1);
                }
                if (!intMap.containsKey(nums[i])) {
                    intMap.put(nums[i], 1);
                    curUniqueNum++;
                } else {
                    if (intMap.get(nums[i]) == 1) {
                        curUniqueNum--;
                    }
                    intMap.put(nums[i], intMap.get(nums[i]) + 1);
                }
            }
        }

        sumUnique += curUniqueNum;

        return sumUnique;
    }
}

