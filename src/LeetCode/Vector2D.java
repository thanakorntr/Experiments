package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 1/30/16.
 */
public class Vector2D {

    Queue<Iterator<Integer>> queue;
    Iterator<Integer> current = null;

    public Vector2D(List<List<Integer>> vec2d) {
        queue = new LinkedList<Iterator<Integer>>();
        for (int i = 0; i < vec2d.size(); i++){
            queue.add(vec2d.get(i).iterator());
        }
        current = queue.poll(); // first
    }

    public int next() {
        if (!current.hasNext()) return -1;

        return current.next();
    }

    public boolean hasNext() {
        if (current == null) return false;

        if (!current.hasNext()) {
            if (queue.isEmpty()) {
                return false;
            } else {
                current = queue.poll();
            }
        }

        return true;
    }

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(1,2);
        List<Integer> l2 = Arrays.asList(3);
        List<Integer> l3 = Arrays.asList(4,5,6);

        List<List<Integer>> lists = Arrays.asList(l1, l2, l3);

        Vector2D vector2D = new Vector2D(lists);

        while (vector2D.hasNext()) {
            System.out.println(vector2D.next());
        }

    }
}
