import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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

    public static void main(String[] args) {
        int[] candleLens = {2,2,2};

        System.out.println(getMaxDay(candleLens));
    }

    public static int getMaxDay(int[] candleLens) {
        Queue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for (Integer i : candleLens) {
            maxHeap.add(i);
        }

        int day = 0;

        while (true) {



            break;
        }

        return day;
    }
}
