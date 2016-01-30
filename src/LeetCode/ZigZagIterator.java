package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 1/30/16.
 */
public class ZigZagIterator implements Iterator<Integer> {

    private Queue<Iterator<Integer>> iterators = new LinkedList<>();

    public ZigZagIterator(List<Integer>... integerLists) {
        if (integerLists != null) {
            for (List<Integer> integerList : integerLists) {
                if (!integerList.isEmpty()) {
                    iterators.add(integerList.iterator());
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Iterator<Integer> iterator = iterators.poll();
            int nextInt = iterator.next();
            if (iterator.hasNext()) {
                iterators.add(iterator);
            }
            return nextInt;
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2);
        List<Integer> l2 = Arrays.asList(3, 4, 5, 6);
        List<Integer> l3 = Arrays.asList(7, 8, 9);
        List<Integer> l4 = Arrays.asList();
        ZigZagIterator zigZagIterator = new ZigZagIterator(l1, l2, l3, l4);
        while (zigZagIterator.hasNext()) {
            System.out.println(zigZagIterator.next());
        }
    }
}
