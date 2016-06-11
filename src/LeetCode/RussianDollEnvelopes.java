package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one
 * envelope is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Example:
 Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you
 can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * Created by Thanakorn on 6/11/16.
 */

class Envelop {
    public int width;
    public int height;

    public Envelop(int width, int height) {
        this.width = width;
        this.height = height;
    }
}

public class RussianDollEnvelopes {

    public static void main(String[] args) {
        RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();
        int[][] envelopes = new int[][]{{5,4}, {6,4}, {6,7}, {2,3}};
        System.out.println(russianDollEnvelopes.maxEnvelopesNsquare(envelopes));  // 3
    }

    // nlogn
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        List<Envelop> envelopList = new ArrayList<>();
        for (int[] env : envelopes) {
            Envelop envelop = new Envelop(env[0], env[1]);
            envelopList.add(envelop);
        }
        envelopList.sort((e1, e2) -> {
            if (e1.width < e2.width) {
                return -1;
            } else if (e1.width > e2.width) {
                return 1;
            }
            return Integer.compare(e2.height, e1.height);
        });

        List<Integer> lisHeight = new ArrayList<>();
        for (Envelop envelop : envelopList) {
            if (lisHeight.isEmpty()) {
                lisHeight.add(envelop.height);
                continue;
            }
            int left = 0, right = lisHeight.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (lisHeight.get(mid) == envelop.height) {
                    break;
                } else if (lisHeight.get(mid) < envelop.height) {
                    if (mid == lisHeight.size() - 1) {
                        lisHeight.add(envelop.height);
                        break;
                    } else if (lisHeight.get(mid + 1) > envelop.height) {
                        lisHeight.set(mid + 1, envelop.height);
                        break;
                    }
                    left = mid + 1;
                } else {  // lisHeight.get(mid) > envelop.height
                    if (mid == 0) {
                        lisHeight.set(0, envelop.height);
                        break;
                    } else if (lisHeight.get(mid - 1) < envelop.height) {
                        lisHeight.set(mid, envelop.height);
                        break;
                    }
                    right = mid - 1;
                }
            }
        }

        return lisHeight.size();
    }

    public int maxEnvelopesNsquare(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        List<Envelop> envelopList = new ArrayList<>();
        for (int[] env : envelopes) {
            Envelop envelop = new Envelop(env[0], env[1]);
            envelopList.add(envelop);
        }
        envelopList.sort((e1, e2) -> Integer.compare(e1.width, e2.width));
        Map<Integer, Integer> memo = new HashMap<>();
        for (int startIndex = 0; startIndex < envelopList.size(); startIndex++) {
            maxEnvelopesHelper(envelopList, startIndex, memo);
        }

        int max = 1;
        for (int startIndex : memo.keySet()) {
            max = Math.max(max, memo.get(startIndex));
        }
        return max;
    }

    private void maxEnvelopesHelper(List<Envelop> envelopList, int startIndex, Map<Integer, Integer> memo) {
        if (startIndex > envelopList.size() || memo.containsKey(startIndex)) {
            return;
        }

        int max = 1;
        for (int i = startIndex + 1; i < envelopList.size(); i++) {
            if (envelopList.get(i).width > envelopList.get(startIndex).width
                    && envelopList.get(i).height > envelopList.get(startIndex).height) {
                if (!memo.containsKey(i)) {
                    maxEnvelopesHelper(envelopList, i, memo);
                }
                max = Math.max(max, 1 + memo.get(i));
            }
        }

        memo.put(startIndex, max);
    }

}
