package LeetCode;

import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class Test {

    public enum DIRECTION {LEFT, RIGHT, UP, DOWN};

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private Stack<Integer> curStack = s1;

    public static void main(String[] args) {

        TreeSet<Integer> s = new TreeSet<>();
        s.add(1);
        s.add(1);
        System.out.println(s.size());
        s.remove(1);
        System.out.println(s.size());
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
