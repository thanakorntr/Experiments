package LeetCodeII;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 5/5/16.
 */
public class AllocateMinimumPages {

    public static void main(String[] args) {
        int numStudents = 2;
        int[] numPages = new int[]{12, 34, 67, 90};
        System.out.println(new AllocateMinimumPages().minimumMaximumPage(numPages, numStudents));  // 113
    }

    public int minimumMaximumPage(int[] pages, int numStudents) {
        if (pages == null || pages.length == 0 || numStudents > pages.length) {
            return -1;
        }

        int M = pages.length;
        Map<Integer, Map<Integer,Integer>> memo = new HashMap<>();
        for (int n = 0; n <= numStudents; n++) {
            memo.put(n, new HashMap<>());
        }
        return minimumMaximumPageHelper(pages, 0, numStudents, memo);
    }

    private int minimumMaximumPageHelper(int[] pages, int startPageIndex, int numStudents, Map<Integer, Map<Integer, Integer>> memo) {
        if (memo.get(numStudents).containsKey(startPageIndex)) {
            return memo.get(numStudents).get(startPageIndex);
        }

        if (numStudents == 1) {
            int sumPage = 0;
            for (int i = startPageIndex; i < pages.length; i++) {
                sumPage += pages[i];
            }
            memo.get(numStudents).put(startPageIndex, sumPage);
            return sumPage;
        }

        if (pages.length - startPageIndex + 1 == numStudents) {
            int maxPage = Integer.MIN_VALUE;
            for (int i = startPageIndex; i < pages.length; i++) {
                maxPage = Math.max(maxPage, pages[i]);
            }
            memo.get(numStudents).put(startPageIndex, maxPage);
            return maxPage;
        }

        int sumPage = pages[startPageIndex];
        int minPage = Integer.MAX_VALUE;
        int nextIndex = startPageIndex + 1;
        while (nextIndex < pages.length && pages.length - nextIndex + 1 >= numStudents - 1) {
            if (!memo.get(numStudents - 1).containsKey(nextIndex)) {
                memo.get(numStudents - 1).put(nextIndex, minimumMaximumPageHelper(pages, nextIndex, numStudents - 1, memo));
            }
            minPage = Math.min(minPage, Math.max(sumPage, memo.get(numStudents - 1).get(nextIndex)));
            sumPage += pages[nextIndex];
            nextIndex++;
        }

        memo.get(numStudents).put(startPageIndex, minPage);
        return minPage;
    }

}
