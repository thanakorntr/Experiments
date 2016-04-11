package LeetCodeII;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 4/10/16.
 */
public class NChooseK {

    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        List<List<Integer>> ans = nChooseK(n, k);
        for (List<Integer> c : ans) {
            System.out.println(c.toString());
        }

    }

    private static List<List<Integer>> nChooseK(int n, int k) {
        if (k > n) {
            throw new IllegalArgumentException();
        }

        List<List<Integer>> ans = new ArrayList<>();

        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> singleAns = new ArrayList<>();
                singleAns.add(i);
                ans.add(singleAns);
            }
            return ans;
        }

        if (n == k) {
            List<Integer> singleAns = new ArrayList<>();
            for (int i = 1; i <= k; i++) {
                singleAns.add(i);
            }
            ans.add(singleAns);
            return ans;
        }

        // it is ensured that n > k at this point
        List<List<Integer>> subAns1 = nChooseK(n - 1, k);
        ans.addAll(subAns1);

        List<List<Integer>> subAns2 = nChooseK(n - 1, k - 1);
        for (List<Integer> sub2 : subAns2) {
            sub2.add(n);
            ans.add(sub2);
        }

        return ans;
    }


}
