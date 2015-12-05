package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thanakorn on 12/5/15.
 */
public class IPsoftFindNonDuplicate {


    public static void main(String[] args) {

        int[] nums = new int[]{1,3,6,1,2,5,1,3,6,3,6,3,1,9,4,3,5,9,4};
        System.out.println(findNonDuplicate(nums));  // 2
    }

    public static int findNonDuplicate(int[] nums) {

        Set<Integer> duplicates = new HashSet<>();
        Set<Integer> nonDuplicates = new HashSet<>();

        for (int num : nums) {
            if (!duplicates.contains(num) && !nonDuplicates.contains(num)) {
                nonDuplicates.add(num);
            } else {
                if (duplicates.contains(num) && nonDuplicates.contains(num)) {
                    nonDuplicates.remove(num);
                } else if (!duplicates.contains(num) && nonDuplicates.contains(num)) {
                    nonDuplicates.remove(num);
                    duplicates.add(num);
                }
            }
        }

        Integer i = nonDuplicates.stream().findFirst().orElse(null);

        return i;
    }
}
