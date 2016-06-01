package LeetCode;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far
 * as a list of disjoint intervals.

 For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

 [1, 1]
 [1, 1], [3, 3]
 [1, 1], [3, 3], [7, 7]
 [1, 3], [7, 7]
 [1, 3], [6, 7]
 Follow up:
 What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 *
 * Created by Thanakorn on 5/31/16.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class SummaryRanges {

    private List<Interval> intervals;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        this.intervals = new ArrayList<>();
    }

    public void addNum(int val) {
        if (intervals.isEmpty()) {
            intervals.add(new Interval(val, val));
            return;
        }
        for (int i = 0; i < intervals.size(); i++) {
            Interval curInterval = intervals.get(i);
            if (val >= curInterval.start && val <= curInterval.end) {
                return;
            }
            if (val + 1 == curInterval.start) {
                if (i - 1 >= 0) {
                    if (intervals.get(i - 1).end + 1 == val) {
                        curInterval.start = intervals.get(i - 1).start;
                        intervals.remove(i - 1);
                        return;
                    } else {
                        curInterval.start = val;
                        return;
                    }
                } else {
                    curInterval.start = val;
                    return;
                }
            }
            if (curInterval.end + 1 == val) {
                if (i + 1 < intervals.size()) {
                    if (intervals.get(i + 1).start - 1 == val) {
                        curInterval.end = intervals.get(i + 1).end;
                        intervals.remove(i + 1);
                        return;
                    } else {
                        curInterval.end = val;
                        return;
                    }
                } else {
                    curInterval.end = val;
                    return;
                }
            }
            if (val < curInterval.start - 1) {
                intervals.add(i, new Interval(val, val));
                return;
            }
        }
        intervals.add(new Interval(val, val));
    }

    public List<Interval> getIntervals() {
        return this.intervals;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
