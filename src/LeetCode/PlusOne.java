package LeetCode;

import java.util.ArrayList;

/**
 * Created by Thanakorn on 6/15/15.
 */
public class PlusOne {

    public static void main(String[] args) {
        ArrayList<Integer> n1 = new ArrayList<>();
        n1.add(9);
        n1.add(9);
        n1.add(9);
        plusOneArray(n1);
        System.out.println(n1.toString());
    }

    public static void plusOneArray(ArrayList<Integer> num) {
        if (num == null || num.size() == 0) {
            return;
        }

        int carry = 1;
        int numCarry = 0;
        for (int i = num.size() - 1; i >= 0; i--) {
            int curDigit = num.get(i);
            if (carry == 1) {
                if (curDigit + 1 == 10) {
                    num.set(i, 0);
                    numCarry++;
                } else {
                    num.set(i, curDigit + 1);
                    break;
                }
            } else {
                break;
            }
        }

        if (numCarry == num.size()) {
            num.add(0);
            num.set(0, 1);
        }
    }

    public static void test(int[] a) {
        a = new int[5];
        for (int i = 0; i < 5; i++) {
            a[i] = i;
        }
    }
}
