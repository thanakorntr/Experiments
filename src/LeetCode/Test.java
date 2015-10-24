package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class Test {

    public enum DIRECTION {LEFT, RIGHT, UP, DOWN};

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private Stack<Integer> curStack = s1;

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,2);
        Iterator<Integer> iterator = nums.iterator();

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }

    private static Collection<Integer[]> tt() {
        Integer[] a = new Integer[3];
        return Arrays.asList(a,a,a,a);
    }

    private static int[] generateRandomTest() {
        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(10 * Math.random()) + 1;
        }
        return nums;
    }

    private static int dpLIS(int[] nums) {

        int maxLen = 1;
        int[] lisArray = new int[nums.length];
        lisArray[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int tmpLen = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    tmpLen = Math.max(tmpLen, lisArray[j] + 1);
                }
            }
            lisArray[i] = tmpLen;
            maxLen = Math.max(maxLen, tmpLen);
        }

        return maxLen;
    }

    private static int bruteForceLIS(int[] nums) {

        int maxLen = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int curLen = 1;
                int lastNum = nums[i];
                for (int k = i; k <= j; k++) {
                    if (nums[k] > lastNum) {
                        lastNum = nums[k];
                        curLen++;
                    }
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }

        return maxLen;

    }

    private static int startIndex(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid < nums.length - 1 && nums[mid] > nums[mid+1]) {
                return mid;
            } else if (nums[left] < nums[mid]) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return left;
    }

    private static int IOB2(int[] nums) {
        try {
            int a = IOB(nums);
            return a;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static int IOB(int[] nums) {
        try {
            int lastNum = nums[3];
            return lastNum;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void printListNodes(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    public static int compare(int a, int b) {
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        } else {
            return 0;
        }
    }

    public static int reverse(int x) {
        if (x >= 0) {
            return Integer.parseInt(getReverse(Integer.toString(x)));
        }

        String rv = getReverse(Integer.toString(-x));
        return -Integer.parseInt(rv);
    }

    public static String getReverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >=0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString().trim();
    }

    public static void printListOfList(List<List<Integer>> l) {
        for (List<Integer> subList : l) {
            System.out.println(subList.toString());
        }
    }
}
