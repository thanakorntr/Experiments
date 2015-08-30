package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 8/29/15.
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

 Therefore, return the max sliding window as [3,3,5,5,6,7].

 Note:
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?

 Hint:

 How about using a data structure such as deque (double-ended queue)?
 The queue size need not be the same as the window’s size.
 Remove redundant elements and the queue should store only elements that need to be considered.

 */
public class SlidingWindowMaximum {

    private class MaxQueue {

        private List<Integer> storage = new ArrayList<>();
        private int maxIntIndex = -1;
        private int startIndex = -1;

        public int getMax() {
            if (maxIntIndex == -1) {
                throw new IndexOutOfBoundsException();
            }
            return storage.get(maxIntIndex);
        }

        public void push(int x) {
            if (maxIntIndex == -1) {
                maxIntIndex = 0;
                startIndex = 0;
                storage.add(x);
                return;
            }
            storage.add(x);
            if (x > getMax()) {
                maxIntIndex = storage.size() - 1;
            }
        }

        public int pop() {
            if (startIndex == -1) {
                throw new IndexOutOfBoundsException();
            }
            if (storage.get(startIndex) != getMax()) {
                int popVal = storage.get(startIndex);
                startIndex++;
                return popVal;
            }
            return 0;
        }
    }

    public static void main(String[] args) {

    }

    public int[] maxSlidingWindow(int[] nums, int k) {


        return null;
    }

}
