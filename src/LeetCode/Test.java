package LeetCode;

import java.util.List;
import java.util.Stack;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class Test {

    public enum DIRECTION {LEFT, RIGHT, UP, DOWN};

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private Stack<Integer> curStack = s1;

    public static void main(String[] args) {

        int a = -2;
        int b = a >> 1;
        int c = a >>> 1;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
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
