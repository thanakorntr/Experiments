package LeetCode;

import java.util.*;

/**
 * http://www.geeksforgeeks.org/two-water-jug-puzzle/
 *
 * You are at the side of a river. You are given a m litre jug and a n litre jug where 0 < m < n.
 * Both the jugs are initially empty. The jugs don’t have markings to allow measuring smaller quantities.
 * You have to use the jugs to measure d litres of water where d < n. Determine the minimum no of operations
 * to be performed to obtain d litres of water in one of jug.
 The operations you can perform are:

 Empty a Jug
 Fill a Jug
 Pour water from one jug to the other until one of the jugs is either empty or full.
 *
 * Created by Thanakorn on 5/11/16.
 */
public class TwoWaterJugPuzzle {

    public static void main(String[] args) {
        TwoWaterJugPuzzle twoWaterJugPuzzle = new TwoWaterJugPuzzle();

        int m = 5, n = 3, d = 4;
        System.out.println(twoWaterJugPuzzle.minOperation(m, n, d));  // 6

        m = 5; n = 3; d = 1;
        System.out.println(twoWaterJugPuzzle.minOperation(m, n, d));
    }

    private int minOperation(int M, int N, int d) {
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        for (int i = 0; i <= Math.max(M, N); i++) {
            visited.put(i, new HashSet<>());
        }

        int numOperation = 0;

        Queue<Integer> curMqueue = new LinkedList<>();
        Queue<Integer> curNqueue = new LinkedList<>();
        curMqueue.add(0);  // start with empty jug
        curNqueue.add(0);  // start with empty jug

        while (!curMqueue.isEmpty()) {
            Queue<Integer> nextMqueue = new LinkedList<>();
            Queue<Integer> nextNqueue = new LinkedList<>();
            while (!curMqueue.isEmpty()) {
                int curM = curMqueue.poll();
                int curN = curNqueue.poll();
                if (curM == d || curN == d) {
                    return numOperation;
                }
                // empty M jug
                int nextM = 0;
                if (!visited.get(nextM).contains(curN)) {
                    nextMqueue.add(nextM);
                    nextNqueue.add(curN);
                    visited.get(nextM).add(curN);
                }
                // full M jug
                nextM = M;
                if (!visited.get(nextM).contains(curN)) {
                    nextMqueue.add(nextM);
                    nextNqueue.add(curN);
                    visited.get(nextM).add(curN);
                }
                // put M to N
                nextM = curM - (N - curN) >= 0 ? curM - (N - curN) : 0;
                int nextN = curN + curM <= N ? curN + curM : N;
                if (!visited.get(nextM).contains(nextN)) {
                    nextMqueue.add(nextM);
                    nextNqueue.add(nextN);
                    visited.get(nextM).add(nextN);
                }

                // empty N jug
                nextN = 0;
                if (!visited.get(curM).contains(nextN)) {
                    nextMqueue.add(curM);
                    nextNqueue.add(nextN);
                    visited.get(curM).add(nextN);
                }
                // full N jug
                nextN = N;
                if (!visited.get(curM).contains(nextN)) {
                    nextMqueue.add(curM);
                    nextNqueue.add(nextN);
                    visited.get(curM).add(nextN);
                }
                // put N to M
                nextN = curN - (M - curM) >= 0 ? curN - (M - curM) : 0;
                nextM = curM + curN <= M ? curM + curN : M;
                if (!visited.get(nextM).contains(nextN)) {
                    nextMqueue.add(nextM);
                    nextNqueue.add(nextN);
                    visited.get(nextM).add(nextN);
                }
            }
            curMqueue = nextMqueue;
            curNqueue = nextNqueue;
            numOperation++;
        }

        return numOperation;
    }

}
