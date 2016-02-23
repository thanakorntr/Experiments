package LeetCodeII;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 *
 * Created by Thanakorn on 2/22/16.
 */
public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> allCom = combine(9, 3);
        for (List<Integer> com : allCom) {
            System.out.println(com.toString());
        }
    }

    public static List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> allCombinations = new ArrayList<>();

        if (k == 1 || n == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> singleArray = new ArrayList<>();
                singleArray.add(i);
                allCombinations.add(singleArray);
            }
            return allCombinations;
        }

        List<List<Integer>> subComs1 = combine(n - 1, k - 1);
        for (List<Integer> subCom1 : subComs1) {
            if (subCom1.size() == k - 1) {
                subCom1.add(n);
                allCombinations.add(subCom1);
            }
        }

        List<List<Integer>> subComs2 = combine(n - 1, k);
        for (List<Integer> subCom2 : subComs2) {
            if (subCom2.size() == k) {
                allCombinations.add(subCom2);
            }
        }

        return allCombinations;

    }


}
