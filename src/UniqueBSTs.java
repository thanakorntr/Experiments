import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 7/11/15.
 */
public class UniqueBSTs {

    public static void main(String[] args) {

        System.out.println(numTrees(3));

    }

    public static int numTrees(int n) {
        if (n < 1) {
            return 0;
        }

        Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
        for (int i = 0; i <= n+1; i++) {
            memo.put(i, new HashMap<>());
        }

        return numTreesHelper(1, n, memo);
    }

    public static int numTreesHelper(int start, int end, Map<Integer, Map<Integer, Integer>> memo) {

        if (memo.get(start).containsKey(end)) {
            return memo.get(start).get(end);
        }

        if (start == end) {
            return 1;
        }

        int sum = 0;

        for (int i = start; i <= end; i++) {
            if (i == start) {
                if (memo.get(start+1).containsKey(end)) {
                    sum += memo.get(start+1).get(end);
                } else {
                    int subSum = numTreesHelper(start+1, end, memo);
                    memo.get(start+1).put(end, subSum);
                    sum += subSum;
                }
            } else if (i == end) {
                if (memo.get(start).containsKey(end-1)) {
                    sum += memo.get(start).get(end-1);
                } else {
                    int subSum = numTreesHelper(start, end-1, memo);
                    memo.get(start).put(end-1, subSum);
                    sum += subSum;
                }
            } else {
                int leftSum;
                if (memo.get(start).containsKey(i-1)) {
                    leftSum = memo.get(start).get(i-1);
                } else {
                    leftSum = numTreesHelper(start, i-1, memo);
                    memo.get(start).put(i-1, leftSum);
                }

                int rightSum;
                if (memo.get(i+1).containsKey(end)) {
                    rightSum = memo.get(i+1).get(end);
                } else {
                    rightSum = numTreesHelper(i+1, end, memo);
                    memo.get(i+1).put(end, rightSum);
                }

                sum += leftSum * rightSum;
            }
        }

        memo.get(start).put(end, sum);
        return sum;
    }
}
