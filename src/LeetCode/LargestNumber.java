package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Thanakorn on 8/12/15.
 */
public class LargestNumber {

    public static void main(String[] args) {

        int[] ar = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(ar));  // 9534330

    }

    public static String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        if (nums == null || nums.length == 0) {
            return sb.toString();
        }

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            list.add(new ArrayList<>());
        }

        boolean containsOnlyZero = true;
        for (int i : nums) {
            if (i != 0) containsOnlyZero = false;
            int index = Integer.parseInt(Integer.toString(i).substring(0,1));
            list.get(index).add(i);
        }

        if (containsOnlyZero) return "0";

        for (int i = 0; i < list.size(); i++) {
            List<Integer> temp = list.get(i);
            Collections.sort(temp, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    String i1 = Integer.toString(o1);
                    String i2 = Integer.toString(o2);
                    String s1 = i1 + i2;
                    String s2 = i2 + i1;
                    for (int j = 0 ; j < s1.length(); j++) {
                        if (s1.charAt(j)  > s2.charAt(j)) {
                            return -1;
                        } else if (s1.charAt(j) < s2.charAt(j)) {
                            return 1;
                        }
                    }
                    return 0;
                }
            });
        }

        for (int i = 9; i >= 0; i--) {
            List<Integer> temp = list.get(i);
            if (!temp.isEmpty()) {
                for (int j : temp) {
                    sb.append(j);
                }
            }
        }

        return sb.toString();
    }

}
