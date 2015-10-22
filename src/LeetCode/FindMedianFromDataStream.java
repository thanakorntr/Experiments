package LeetCode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Thanakorn on 10/21/15.
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value. So the median is the mean of the two middle value.

 Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.
 For example:

 add(1)
 add(2)
 findMedian() -> 1.5
 add(3)
 findMedian() -> 2
 */
public class FindMedianFromDataStream {

    class MedianFinder {

        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Adds a number into the data structure.
        public void addNum(int num) {
            if (maxHeap.size() == minHeap.size()) {
                if (maxHeap.size() == 0) {
                    maxHeap.add(num);
                } else {
                    if (num >= maxHeap.peek()) {
                        minHeap.add(num);
                    } else {
                        maxHeap.add(num);
                    }
                }
            } else if (maxHeap.size() > minHeap.size()) {
                if (num >= maxHeap.peek()) {
                    minHeap.add(num);
                } else {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(num);
                }
            } else { // maxHeap.size() < minHeap.size()
                if (num <= minHeap.peek()) {
                    maxHeap.add(num);
                } else {
                    maxHeap.add(minHeap.poll());
                    minHeap.add(num);
                }
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            if (minHeap.size() == maxHeap.size()) {
                return (minHeap.peek() + maxHeap.peek()) / 2D;
            }
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return minHeap.peek();
        }
    };

}
