package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *  There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.

 What is the minimum candies you must give?
 *
 * Created by Thanakorn on 4/2/16.
 */
public class Candy {

    public static void main(String[] args) {
        int[] ratings = new int[] {2,2};
        System.out.println(new Candy().candy(ratings));
    }

    private class CandyWrapper implements Comparable<CandyWrapper> {
        int index;
        int rating;

        public CandyWrapper(int index, int rating) {
            this.index = index;
            this.rating = rating;
        }

        @Override
        public int compareTo(CandyWrapper o) {
            return Integer.compare(this.rating, o.rating);
        }
    }

    public int candy(int[] ratings) {

        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] candyArray = new int[ratings.length];
        Arrays.fill(candyArray, 1);
        int numCandies = ratings.length;

        PriorityQueue<CandyWrapper> pq = new PriorityQueue<>();

        for (int index = 0; index < ratings.length; index++) {
            CandyWrapper candyWrapper = new CandyWrapper(index, ratings[index]);
            pq.add(candyWrapper);
        }

        while (!pq.isEmpty()) {
            CandyWrapper candyWrapper = pq.poll();
            int leftIndex = candyWrapper.index - 1;
            int rightIndex = candyWrapper.index + 1;
            if (leftIndex >= 0) {
                if (ratings[leftIndex] > ratings[candyWrapper.index]
                        && candyArray[leftIndex] <= candyArray[candyWrapper.index]) {
                    int diff = candyArray[candyWrapper.index] - candyArray[leftIndex];
                    candyArray[leftIndex] += diff + 1;
                    numCandies += diff + 1;
                }
            }
            if (rightIndex < ratings.length) {
                if (ratings[rightIndex] > ratings[candyWrapper.index]
                        && candyArray[rightIndex] <= candyArray[candyWrapper.index]) {
                    int diff = candyArray[candyWrapper.index] - candyArray[rightIndex];
                    candyArray[rightIndex] += diff + 1;
                    numCandies += diff + 1;
                }
            }
        }

        return numCandies;

    }

}
