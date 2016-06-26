package LeetCode;


import java.util.*;

/**
 * You are given two jugs with capacities x and y litres.
 * There is an infinite amount of water supply available.
 * You need to determine whether it is possible to measure exactly z litres using
 * these two jugs.

 If z liters of water is measurable, you must have z liters of water contained
 within one or both buckets by the end.

 Operations allowed:

 Fill any of the jugs completely with water.
 Empty any of the jugs.
 Pour water from one jug into another till the other jug is completely full or
 the first jug itself is empty.
 Example 1: (From the famous "Die Hard" example)

 Input: x = 3, y = 5, z = 4
 Output: True
 Example 2:

 Input: x = 2, y = 6, z = 5
 Output: False
 *
 * Created by Thanakorn on 6/25/16.
 */
class JugPair {

    public int x;
    public int y;

    public JugPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return this.x == ((JugPair)o).x && this.y == ((JugPair)o).y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

}

public class WaterAndJug {

    public static void main(String[] args) {
        WaterAndJug waterAndJug = new WaterAndJug();
        int x = 3, y = 5, z = 4;
        System.out.println(waterAndJug.canMeasureWater(x, y, z));  // true

        x = 2; y = 6; z = 5;
        System.out.println(waterAndJug.canMeasureWater(x, y, z));  // false

        x = 1; y = 2; z = 3;
        System.out.println(waterAndJug.canMeasureWater(x, y, z));  // true

        x = 104639; y = 104651; z = 234;
        System.out.println(waterAndJug.canMeasureWater(x, y, z));
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }
        Set<JugPair> visited = new HashSet<>();
        Queue<JugPair> queue = new LinkedList<>();
        queue.add(new JugPair(0, 0));
        visited.add(new JugPair(0, 0));
        while (!queue.isEmpty()) {
            Queue<JugPair> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                JugPair jugPair = queue.poll();
                List<JugPair> jugPairs = performOperation(jugPair, x, y);
                for (JugPair nextJugPair : jugPairs) {
                    if (!visited.contains(nextJugPair)) {
                        if (nextJugPair.x == z || nextJugPair.y == z || nextJugPair.x + nextJugPair.y == z) {
                            return true;
                        }
                        visited.add(nextJugPair);
                        nextQueue.add(nextJugPair);
                    }
                }
            }
            queue = nextQueue;
        }

        return false;
    }

    private List<JugPair> performOperation(JugPair jugPair, int x, int y) {
        List<JugPair> jugPairs = new ArrayList<>();

        // empty x
        if (jugPair.x != 0) {
            jugPairs.add(new JugPair(0, jugPair.y));
        }
        // fill x
        if (jugPair.x != x) {
            jugPairs.add(new JugPair(x, jugPair.y));
        }
        // transfer x to y
        if (jugPair.x != 0 && jugPair.y != y) {
            int emptyY = y - jugPair.y;
            int newX = jugPair.x - emptyY >= 0 ? jugPair.x - emptyY : 0;
            int newY = jugPair.x >= emptyY ? y : jugPair.y + jugPair.x;
            jugPairs.add(new JugPair(newX, newY));
        }

        // empty y
        if (jugPair.y != 0) {
            jugPairs.add(new JugPair(jugPair.x, 0));
        }
        // fill y
        if (jugPair.y != y) {
            jugPairs.add(new JugPair(jugPair.x, y));
        }
        // transfer y to x
        if (jugPair.y != 0 && jugPair.x != x) {
            int emptyX = x - jugPair.x;
            int newX = jugPair.y >= emptyX ? x : jugPair.x + jugPair.y;
            int newY = jugPair.y - emptyX >= 0 ? jugPair.y - emptyX : 0;
            jugPairs.add(new JugPair(newX, newY));
        }

        return jugPairs;
    }

}
