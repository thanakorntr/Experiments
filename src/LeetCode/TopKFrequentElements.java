package LeetCode;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * Created by Thanakorn on 5/3/16.
 */
public class TopKFrequentElements {

    private class TopKpair implements Comparable<TopKpair> {

        public int key;
        public int count;

        public TopKpair(int key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(TopKpair o) {
            return Integer.compare(count, o.count);
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> topK = new ArrayList<>();

        if (nums == null || nums.length < k) {
            return topK;
        }

        Map<Integer, TopKpair> topKmap = new HashMap<>();
        for (int num : nums) {
            if (!topKmap.containsKey(num)) {
                topKmap.put(num, new TopKpair(num, 0));
            }
            topKmap.get(num).count = topKmap.get(num).count + 1;
        }

        Queue<TopKpair> minHeap = new PriorityQueue<>();
        for (int num : topKmap.keySet()) {
            minHeap.add(topKmap.get(num));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            topK.add(minHeap.poll().key);
        }

        return topK;
    }

}
