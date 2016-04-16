package LeetCode;

/**
 * Created by Thanakorn on 4/16/16.
 */

import java.util.*;

/**
 *Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Given the list [1,[4,[6]]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        List<NestedInteger> nestedIntegers = new ArrayList<>();
        NestedIterator nestedIterator = new NestedIterator(nestedIntegers);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }

    private class NestedIteratorWrapper implements Iterator<Integer> {

        private Integer integer;
        private NestedIterator nestedIterator;
        private boolean isInteger;

        public NestedIteratorWrapper(NestedInteger nestedInteger) {
            if (nestedInteger.isInteger()) {
                integer = nestedInteger.getInteger();
                isInteger = true;
            } else {
                nestedIterator = new NestedIterator(nestedInteger.getList());
            }
        }

        @Override
        public boolean hasNext() {
            if (isInteger) {
                return integer != null;
            } else {
                return nestedIterator.hasNext();
            }
        }

        @Override
        public Integer next() {
            if (isInteger) {
                if (integer != null) {
                    Integer i = integer;
                    integer = null;
                    return i;
                } else {
                    return null;
                }
            }
            return nestedIterator.next();
        }

    }

    private Queue<NestedIteratorWrapper> iteratorQueue = new LinkedList<>();
    private NestedIteratorWrapper curIterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger != null) {
                iteratorQueue.add(new NestedIteratorWrapper(nestedInteger));
            }
        }
        if (!iteratorQueue.isEmpty()) {
            curIterator = iteratorQueue.poll();
        }
    }

    @Override
    public Integer next() {
        return curIterator.next();
    }

    @Override
    public boolean hasNext() {
        if (curIterator != null && curIterator.hasNext()) {
            return true;
        }
        while (!iteratorQueue.isEmpty()) {
            curIterator = iteratorQueue.poll();
            if (curIterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
