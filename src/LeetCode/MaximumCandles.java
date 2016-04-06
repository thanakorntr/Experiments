package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 6/13/15.
 */

/*
You are given n candles with size of each candle present as an element in an integer array i.e a[i] represent length of ith candle.
There is a tradition according to which you celebrate by lighting a candle every night.
The candles are lit such that there are k candles lit on the kth day.
For ex. 1 candle on day 1 , 2 candles on day 2 and so on. Each night a candle burns 1 inch.
You need to write a program to find out maximum number of days you can celebrate the tradition with the n candles and their lengths?
*/

public class MaximumCandles {

    private class CandleWrapper implements Comparable<CandleWrapper> {

        int len;

        protected CandleWrapper(int len) {
            this.len = len;
        }

        @Override
        public int compareTo(CandleWrapper o) {
            return Integer.compare(this.len, o.len);
        }

    }

    public static void main(String[] args) {
        int[] candleLens = {2,2,2};
        System.out.println(new MaximumCandles().getMaxDay(candleLens));
    }

    public int getMaxDay(int[] candleLens) {
        Queue<CandleWrapper> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int candleLen : candleLens) {
            if (candleLen > 0) {
                CandleWrapper candleWrapper = new CandleWrapper(candleLen);
                maxHeap.add(candleWrapper);
            }
        }

        int curDay = 1;

        while (maxHeap.size() >= curDay) {
            List<CandleWrapper> candleList = new ArrayList<>();

            for (int i = 0; i < curDay; i++) {
                CandleWrapper candleWrapper = maxHeap.poll();
                candleWrapper.len = candleWrapper.len - 1;
                if (candleWrapper.len > 0) {
                    candleList.add(candleWrapper);
                }
            }

            for (CandleWrapper candleWrapper : candleList) {
                maxHeap.add(candleWrapper);
            }

            curDay++;
        }

        return curDay - 1;
    }
}
