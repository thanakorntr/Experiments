package LeetCodeII;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * weights = array of bear weights, swap any two adjacent bear only if weight sum
 * is less than or equal to k
 *
 * find max number of arrangements due to swapping
 *
 * Created by Thanakorn on 3/30/16.
 */
public class MaxArrangementOfBears {

    public static void main(String[] args) {
        List<Integer> weights = Lists.newArrayList(1,2,3);
        int k = 3;
        System.out.println(maxArrangementOfBears(weights, k));
    }

    public static int maxArrangementOfBears(List<Integer> weights, int k) {
        if (weights == null || weights.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Queue<List<Integer>> curLevel = new LinkedList<>();
        curLevel.add(weights);

        Set<List<Integer>> visitedArrangements = new HashSet<>();
        visitedArrangements.add(weights);

        while (!curLevel.isEmpty()) {
            Queue<List<Integer>> nextLevel = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                List<Integer> arrangement = curLevel.poll();
                for (int i = 0; i < arrangement.size() - 1; i++) {
                    if (arrangement.get(i) + arrangement.get(i + 1) <= k) {
                        swap(arrangement, i, i + 1);
                        if (!visitedArrangements.contains(arrangement)) {
                            List<Integer> newArrangement = new ArrayList<>(arrangement);
                            visitedArrangements.add(newArrangement);
                            nextLevel.add(newArrangement);
                        }
                        swap(arrangement, i, i + 1);
                    }
                }
            }
            curLevel = nextLevel;
        }

        return visitedArrangements.size();
    }

    private static void swap(List<Integer> nums, int i, int j) {
        int tmp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, tmp);
    }

}
